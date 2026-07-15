package com.auxiongg.restaurant.users.staff;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateStaffProfileRequest {
    @NotNull(message = "first name is required")
    private String firstName;
    @NotNull(message = "last name is required")
    private String lastName;
}
