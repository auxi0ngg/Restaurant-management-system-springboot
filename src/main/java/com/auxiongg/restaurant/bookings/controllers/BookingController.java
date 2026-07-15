package com.auxiongg.restaurant.bookings.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.auxiongg.restaurant.bookings.dtos.BookingDto;
import com.auxiongg.restaurant.bookings.dtos.CreateBookingRequest;
import com.auxiongg.restaurant.bookings.dtos.GetAvailableTablesRequest;
import com.auxiongg.restaurant.bookings.dtos.StaffCoverDto;
import com.auxiongg.restaurant.bookings.entities.RestaurantTable;
import com.auxiongg.restaurant.bookings.services.BookingService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bookings")
@Tag(name = "Bookings")
class BookingController {
    private final BookingService bookingService;

    @GetMapping("/available-tables")
    @Operation(summary = "Any user can view available seating for a given time and date")
    public ResponseEntity<List<RestaurantTable>> getAvailableTables(
            @Valid @RequestBody GetAvailableTablesRequest request
    ) {

        var tables = bookingService.getAvailableTables(request);
        return ResponseEntity.ok(tables);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @Operation(summary = "A manager can view the restaurant's required " +
            " daily staff cover, given the number of bookings")
    @GetMapping("/staff-cover")
    public ResponseEntity<StaffCoverDto> getStaffCover() {
        var staffCoverDto = bookingService.getStaffCover();
        return new ResponseEntity<>(staffCoverDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(summary = "A  customer can book a table for their seating requirements." +
            " Once a table has been booked it will no longer be bookable." +
            " Bookings a limited to one per day per customer")
    @PostMapping
    public ResponseEntity<BookingDto> createBooking(
            @Valid @RequestBody CreateBookingRequest request) {
        var bookingDto = bookingService.createBooking(request);

        return new ResponseEntity<>(bookingDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('WAITER', 'MANAGER')")
    @Operation(summary = "A waiter or manager can approve a booking")
    @PostMapping("/{id}/approve-booking")
    public ResponseEntity<Void> approveBooking(@PathVariable Long id) {
        bookingService.approveBooking(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(summary = "A customer can cancel a booking")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

}
