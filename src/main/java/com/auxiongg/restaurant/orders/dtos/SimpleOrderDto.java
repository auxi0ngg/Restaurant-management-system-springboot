package com.auxiongg.restaurant.orders.dtos;


import lombok.Data;

import java.math.BigDecimal;

import com.auxiongg.restaurant.orders.OrderStatus;

@Data
public class SimpleOrderDto implements OrderDto {
    private Long id;
    private OrderStatus orderStatus;
    private BigDecimal totalPrice;
}
