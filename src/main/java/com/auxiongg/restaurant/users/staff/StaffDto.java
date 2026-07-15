package com.auxiongg.restaurant.users.staff;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class StaffDto {
    private String firstName;
    private String lastName;
    private List<StaffShiftDto> shifts;
    private int hoursWorked;
}
