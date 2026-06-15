package com.camp.mini.service;

import com.camp.mini.dto.OrderDto.Create;
import com.camp.mini.dto.OrderDto.Response;
import java.util.List;

public interface OrderService {
    Long createOrder(Create createDto);

    Response getOrder(Long id);

    List<Response> getOrders();
}
