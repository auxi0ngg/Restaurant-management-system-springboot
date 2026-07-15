package com.auxiongg.restaurant.orders.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.UUID;

@Data
public class CreateDeliveryOrderRequest {
    @NotNull(message = "cart id is required")
    private UUID cartId;
    @NotNull(message = "delivery time is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime deliveryTime;
}
