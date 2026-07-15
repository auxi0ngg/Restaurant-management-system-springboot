package com.auxiongg.restaurant.carts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.auxiongg.restaurant.carts.dtos.CartDto;
import com.auxiongg.restaurant.carts.dtos.CartItemDto;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression = "java(cart.calculateTotalPrice())")
    CartDto toCartDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.calculateTotalPrice())")
    CartItemDto toCartItemDto(CartItem cartItem);

}
