package com.auxiongg.restaurant.events;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.auxiongg.restaurant.events.dtos.CreateEventRequest;
import com.auxiongg.restaurant.events.dtos.CreateEventResponse;

import java.util.List;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
@Tag(name = "Events")
public class EventController {
    private final EventService eventService;

    @GetMapping
    @Operation(summary = "Any user can view all upcoming events")
    public ResponseEntity<List<EventDto>> getAllUpcomingEvents() {
        var events = eventService.getAllUpcomingEvents();
        return ResponseEntity.ok().body(events);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    @Operation(summary = "A customer can create a new event")
    public ResponseEntity<CreateEventResponse> create(@RequestBody CreateEventRequest request) {
        var eventResponse = eventService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(eventResponse);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/{id}/approve")
    @Operation(summary = "A manager can approve an event created by a customer")
    public ResponseEntity<Void> approve(@PathVariable Long id) {
        eventService.approve(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("/{id}/complete")
    @Operation(summary = "A manager can mark an event as complete")
    public ResponseEntity<Void> complete(@PathVariable Long id) {
        eventService.complete(id);
        return ResponseEntity.ok().build();
    }

}
