package com.auxiongg.restaurant.bookings.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class GetAvailableTablesRequest {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @FutureOrPresent(message = "Date cannot be in the past")
    @NotNull(message = "Booking date is required")
    @Future(message = "booking date must be in the future")
    private LocalDate bookingDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @NotNull(message = "Booking time is required")
    private LocalTime startTime;
    @Min(value = 15, message = "You can extend your booking from 15 to 60 minutes")
    @Max(value = 60, message = "You can extend your booking from 15 to 60 minutes")
    private Integer bookingExtension;
}
