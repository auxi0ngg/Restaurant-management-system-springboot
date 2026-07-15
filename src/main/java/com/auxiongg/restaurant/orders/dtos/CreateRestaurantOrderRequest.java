package com.auxiongg.restaurant.orders.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateRestaurantOrderRequest {
    @NotNull(message = "cart id is required")
    private UUID cartId;
}
