package com.camp.mini.domain;

import com.camp.mini.dto.ProductDto;
import com.camp.mini.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Integer price;
    private Integer stock;

    @Enumerated(EnumType.STRING)
    private Status.Product status;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @Column(nullable = false)
    private LocalDateTime updateDate;

    @PrePersist
    public void prePersist() {
        this.stock = 0;
        this.status = Status.Product.ACTIVE;
        this.createDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updateDate = LocalDateTime.now();
    }

    public void update(ProductDto.Update update) {
        if(update.getName() != null){
            this.name = update.getName();
        }
        if (update.getPrice() != null){
            this.price = update.getPrice();
        }
    }

    public void delete(){
        this.status = Status.Product.DELETE;
    }
}
