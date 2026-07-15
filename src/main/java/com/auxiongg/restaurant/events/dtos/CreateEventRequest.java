package com.auxiongg.restaurant.events.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class CreateEventRequest {
    @NotNull(message = "name is required")
    @Size(max = 200, message = "name must no more than 200 characters")
    private String name;
    @NotNull(message = "date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future(message = "event must be in the future")
    private LocalDate date;
    @NotNull(message = "time is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime time;
    @NotNull(message = "table needed is required")
    @Max(value = 10, message = "no more than 10 table available")
    private Integer tablesRequired;
    @NotNull(message = "maximum attendees is required")
    @Max(value = 50, message = "Attendees can be no more than 50")
    private Integer maxAttendees;
    @NotNull(message = "description is required")
    @Size(max = 500, message = "description must be no longer than 500 characters")
    private String description;

}
