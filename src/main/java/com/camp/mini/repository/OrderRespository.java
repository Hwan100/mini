package com.camp.mini.repository;

import com.camp.mini.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRespository extends JpaRepository<Order, Long> {
}
