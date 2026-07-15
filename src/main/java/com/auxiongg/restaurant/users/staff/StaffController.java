package com.auxiongg.restaurant.users.staff;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("staff")
@AllArgsConstructor
@Tag(name = "Staff")
public class StaffController {
    private final StaffService staffService;

    @PreAuthorize("hasAnyRole('WAITER', 'CHEF', 'DELIVERY_DRIVER', 'MANAGER')")
    @PostMapping
    @Operation(summary = "A staff member can create a profile")
    public ResponseEntity<StaffDto> createProfile(
            @Valid @RequestBody CreateStaffProfileRequest request
    ) {
        var staffDto = staffService.createStaff(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(staffDto);

    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("{id}/shifts")
    @Operation(summary = "A manager can add shifts for a chef, waiter or delivery driver")
    public ResponseEntity<StaffDto> addShift(
            @PathVariable Long id,
            @Valid @RequestBody AddShiftToStaffRequest request
    ) {
         var staffDto = staffService.addShift(request.getShiftId(), id, request.getDate());
         return ResponseEntity.status(HttpStatus.CREATED).body(staffDto);
    }

    @PreAuthorize("hasAnyRole('WAITER', 'CHEF', 'DELIVERY_DRIVER', 'MANAGER')")
    @GetMapping("/me")
    @Operation(summary = "A staff member can view their profile")
    public ResponseEntity<StaffDto> me() {
        var staffDto = staffService.me();

        return ResponseEntity.ok(staffDto);
    }

}
