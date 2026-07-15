package com.auxiongg.restaurant.carts.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddItemToCartRequest {
    @NotNull(message = "Menu Item Cannot Be Null")
    private Long itemId;
}
