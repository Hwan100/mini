package com.camp.mini.service;

import com.camp.mini.dto.OrderDto.Create;
import com.camp.mini.dto.OrderDto.Response;
import org.springframework.data.domain.Page;

public interface OrderService {
    Long createOrder(Create createDto);

    Response getOrder(Long id);

    Page<Response> getOrders(int page, int size);
}
