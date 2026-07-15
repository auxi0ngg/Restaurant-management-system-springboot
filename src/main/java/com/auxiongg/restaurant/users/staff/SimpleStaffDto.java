package com.auxiongg.restaurant.users.staff;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SimpleStaffDto {
    private String firstName;
    private String lastName;
    private int hoursWorked;
}
