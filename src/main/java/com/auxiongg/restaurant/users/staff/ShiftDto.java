package com.auxiongg.restaurant.users.staff;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ShiftDto {
    private LocalTime startTime;
    private LocalTime endTime;
}
