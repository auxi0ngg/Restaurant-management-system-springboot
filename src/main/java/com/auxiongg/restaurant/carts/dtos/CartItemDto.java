package com.auxiongg.restaurant.carts.dtos;

import lombok.Data;

import java.math.BigDecimal;

import com.auxiongg.restaurant.menu.dtos.MenuItemDto;

@Data
public class CartItemDto {
    private MenuItemDto menuItem;
    private Integer quantity;
    private BigDecimal totalPrice;
}
