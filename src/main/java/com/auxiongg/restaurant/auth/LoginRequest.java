package com.auxiongg.restaurant.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotNull(message = "email is required")
    @Email
    private String email;
    @NotNull(message = "password is required")
    private String password;
}
