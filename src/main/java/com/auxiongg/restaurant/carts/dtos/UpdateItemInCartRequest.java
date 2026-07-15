package com.auxiongg.restaurant.carts.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateItemInCartRequest {
    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity cannot be less than one")
    @Max(value = 10, message = "Quantity cannot be greater than ten")
    private Integer quantity;
}
