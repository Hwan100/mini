package com.camp.mini.repository;

import com.camp.mini.domain.Product;
import com.camp.mini.enums.Status;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByStatus(Status.Product status);
}
