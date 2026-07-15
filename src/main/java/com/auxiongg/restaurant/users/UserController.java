package com.auxiongg.restaurant.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auxiongg.restaurant.users.customers.CustomerService;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Tag(name = "Users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "A user can register an account with role 'CUSTOMER', 'MANAGER', 'CHEF', 'WAITER' or 'DELIVERY_DRIVER'")
    public ResponseEntity<UserDto> registerUser(
            @Valid @RequestBody RegisterUserRequest request) {
        var userDto = userService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
}
