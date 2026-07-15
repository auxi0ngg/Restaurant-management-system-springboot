package com.auxiongg.restaurant.events;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
public class EventDto {
    private String name;
    private LocalDate date;
    private LocalTime time;
    private String description;
}
