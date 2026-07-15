package com.auxiongg.restaurant.orders.dtos;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TakeawayResponseDto implements OrderDto {
    private Long id;
    private String orderStatus;
    private LocalTime pickupTime;

}
