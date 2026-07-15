package com.auxiongg.restaurant.users.staff;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class StaffShiftDto {
    private ShiftDto shift;
    private LocalDate date;
}
