package com.auxiongg.restaurant.orders.dtos;

import lombok.Data;

import java.math.BigDecimal;

import com.auxiongg.restaurant.menu.dtos.MenuItemDto;

@Data
public class OrderItemDto {
    private MenuItemDto menuItem;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
