package com.auxiongg.restaurant.users.customers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.auxiongg.restaurant.events.EventDto;

@RestController
@PreAuthorize("hasRole('CUSTOMER')")
@RequestMapping("/customers")
@AllArgsConstructor
@Tag(name = "Customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/me")
    @Operation(summary = "A customer can view their profile")
    public ResponseEntity<CustomerDto> me() {
        return ResponseEntity.ok(customerService.me());
    }

    @PostMapping
    @Operation(summary = "A user with role 'CUSTOMER' can create a profile")
    public ResponseEntity<CustomerDto> createCustomerProfile(
            @Valid @RequestBody CreateCustomerProfileRequest request
    ) {
        var customerDto = customerService.createCustomerProfile(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(customerDto);

    }

    @PostMapping("/events")
    @Operation(summary = "Register from an upcoming event" +
            " providing there are spaces available")
    public ResponseEntity<EventDto> addEvent(
            @Valid @RequestBody AddEventToCustomer request
    ) {
        var eventDto = customerService.addEvent(request.getEventId());
        return ResponseEntity.status(HttpStatus.CREATED).body(eventDto);
    }


}
