package com.camp.mini.service;

import com.camp.mini.domain.Order;
import com.camp.mini.domain.Product;
import com.camp.mini.dto.OrderDto;
import com.camp.mini.dto.OrderDto.Create;
import com.camp.mini.dto.OrderDto.Response;
import com.camp.mini.repository.OrderRespository;
import com.camp.mini.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final ProductRepository productRepository;
    private final OrderRespository orderRespository;

    @Override
    public Long createOrder(Create createDto) {

        Product product = productRepository.findById(createDto.getProductId())
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

        orderRespository.save(order);

        return order.getId();
    }

    @Override
    public Response getOrder(Long id) {
        Order order = orderRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("주문이 없습니다."));

        return OrderDto.Response.toDto(order);
    }

    @Override
    public List<Response> getOrders() {
        //내일 여기 페이지네이션하셈, n+1 안되게

        return List.of();
    }
}
