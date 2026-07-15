package com.auxiongg.restaurant.bookings.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class TimeSlotDto {
    private LocalTime startTime;
}
