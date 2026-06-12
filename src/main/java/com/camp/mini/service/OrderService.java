package com.camp.mini.service;

import com.camp.mini.dto.OrderDto.Create;
import com.camp.mini.dto.OrderDto.Response;

public interface OrderService {
    Long createOrder(Create createDto);

    Response getOrder(Long id);
}
