package com.auxiongg.restaurant.menu.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateSpecialRequest {
    @NotNull(message = "name is required")
    @Size(max = 20, message = "name must be less than 20 characters")
    private String name;
    @NotNull(message = "description is required")
    @Size(max = 100, message = "description must be less than 100 characters")
    private String description;
    @NotNull(message = "price is required")
    @DecimalMax(value = "300.00", message = "price must be less than 300")
    private BigDecimal price;
}
