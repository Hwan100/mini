package com.camp.mini.controller;

import com.camp.mini.dto.OrderDto;
import com.camp.mini.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    //주문 생성
    @PostMapping
    public ResponseEntity<Long> createOrder(@Valid @RequestBody OrderDto.Create createDto) {
        System.out.println(createDto.getProductId());
        Long id = orderService.createOrder(createDto);
        return ResponseEntity.ok(id);
    }

    //주문 조회
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto.Response> getOrder(@PathVariable Long id) {
        OrderDto.Response order = orderService.getOrder(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<Page<OrderDto.Response>> getOrders(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "3") int size) {
        Page<OrderDto.Response> orders = orderService.getOrders(page, size);
        return ResponseEntity.ok(orders);
    }
}
