package com.auxiongg.restaurant.orders.dtos;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class DetailedOrderDto implements OrderDto {
    private Long id;
    private String orderStatus;
    private Set<OrderItemDto> orderItems;
    private BigDecimal totalPrice;
}
