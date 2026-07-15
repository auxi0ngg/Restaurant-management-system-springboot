package com.auxiongg.restaurant.carts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

import com.auxiongg.restaurant.menu.MenuItem;

@Data
@AllArgsConstructor
public class CreateCartResponse {
    private UUID id;
    private MenuItem item;
}
