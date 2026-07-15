package com.auxiongg.restaurant.menu;

import org.mapstruct.Mapper;

import com.auxiongg.restaurant.menu.dtos.MenuItemDto;

@Mapper(componentModel = "spring")
public interface MenuMapper {
    MenuItemDto toDto(MenuItem item);
}
