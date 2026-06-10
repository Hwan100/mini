package com.camp.mini.dto;

import com.camp.mini.domain.Product;
import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class ProductDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create{

        @NotBlank(message = "이름은 필수입니다.")
        private String name;
        private Integer price;
        private Integer stock;

        public Product toEntity(){
            return Product.builder()
                    .name(this.name)
                    .price(this.price)
                    .stock(this.stock)
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private Long id;
        private String name;
        private Integer price;
        private Integer stock;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        public static Response toDto(Product product){
            return Response.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .stock(product.getStock())
                    .createDate(product.getCreateDate())
                    .updateDate(product.getUpdateDate())
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update{
        private String name;
        private Integer price;
    }
}
