package com.camp.mini.dto;

import com.camp.mini.domain.Order;
import com.camp.mini.enums.Status;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class OrderDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        private Long productId;
        private Integer quantity;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {
        private Long id;
        private String name;
        private Integer orderPrice;
        private Integer quantity;
        private Status.Order status;
        private LocalDateTime createDate;
        private LocalDateTime updateDate;

        public static Response toDto(Order order) {
            return Response.builder()
                    .id(order.getId())
                    .name(order.getProduct().getName())
                    .orderPrice(order.getOrderPrice())
                    .quantity(order.getQuantity())
                    .status(order.getStatus())
                    .createDate(order.getCreateDate())
                    .updateDate(order.getUpdateDate())
                    .build();
        }
    }
}
