package com.camp.mini.service;

import com.camp.mini.domain.Order;
import com.camp.mini.domain.Product;
import com.camp.mini.dto.OrderDto;
import com.camp.mini.dto.OrderDto.Create;
import com.camp.mini.dto.OrderDto.Response;
import com.camp.mini.repository.OrderRepository;
import com.camp.mini.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Override
    public Long createOrder(Create createDto) {

        Product product = productRepository.findByIdWithLock(createDto.getProductId())
                .orElseThrow(() -> new RuntimeException("상품이 없습니다."));

        if (product.getStock() < createDto.getQuantity()) {
            throw new IllegalArgumentException("재고부족");
        }

        Order order = Order.builder()
                .product(product)
                .quantity(createDto.getQuantity())
                .orderPrice(product.getPrice())
                .build();

        product.setStock(
                product.getStock() - createDto.getQuantity()
        );

        orderRepository.save(order);

        return order.getId();
    }

    @Override
    public Response getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("주문이 없습니다."));

        return OrderDto.Response.toDto(order);
    }

    @Override
    public Page<Response> getOrders(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Order> orders = orderRepository.findAll(pageable);

        return orders.map(OrderDto.Response::toDto);

    }
}
