package com.auxiongg.restaurant.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotNull(message = "email is required")
    @Email
    private String email;
    @NotNull(message = "password is required")
    @Size(min = 6, max = 15, message = "password must be between 6 and 15 characters")
    private String password;
    @NotNull(message = "role is required")
    private Role role;
}
