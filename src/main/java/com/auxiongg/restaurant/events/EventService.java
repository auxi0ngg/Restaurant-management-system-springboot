package com.auxiongg.restaurant.events;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.auxiongg.restaurant.auth.AuthService;
import com.auxiongg.restaurant.events.dtos.CreateEventRequest;
import com.auxiongg.restaurant.events.dtos.CreateEventResponse;
import com.auxiongg.restaurant.events.exceptions.EventAlreadyApprovedException;
import com.auxiongg.restaurant.events.exceptions.EventComplete;
import com.auxiongg.restaurant.events.exceptions.EventNotFoundException;
import com.auxiongg.restaurant.events.exceptions.SameDayEventException;
import com.auxiongg.restaurant.users.customers.Customer;
import com.auxiongg.restaurant.users.customers.CustomerNotFoundException;
import com.auxiongg.restaurant.users.customers.CustomerRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private final AuthService authService;
    private final CustomerRepository customerRepository;
    private final EventMapper eventMapper;
    private final EventRepository eventRepository;

    public CreateEventResponse create(CreateEventRequest request) {
        var customer = getAuthenticatedCustomer();

        if (eventRepository.existsByDateAndCustomer(request.getDate(), customer)) {
            throw new SameDayEventException();
        }

        var event = eventMapper.toEntity(request);
        event.setCustomer(customer);
        event.setStatus(EventStatus.PENDING);
        eventRepository.save(event);

        return new CreateEventResponse(
                event.getId(),
                event.getName(),
                event.getDate(),
                event.getTime(),
                event.getDescription());

    }

    public void approve(Long id) {
        var event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            throw new EventNotFoundException();
        }
        if (event.getStatus() != EventStatus.PENDING) {
            throw new EventAlreadyApprovedException();
        }

        event.setStatus(EventStatus.APPROVED);
        eventRepository.save(event);
    }

    public void complete(Long id) {
        var event = eventRepository.findById(id).orElse(null);
        if (event == null) {
            throw new EventNotFoundException();
        }
        if (event.getStatus() != EventStatus.APPROVED) {
            throw new EventComplete();
        }

        event.setStatus(EventStatus.FINISHED);
        eventRepository.save(event);
    }

    public List<EventDto> getAllUpcomingEvents() {
        var events = eventRepository.findAllByStatusAndAfterToday(EventStatus.APPROVED, LocalDate.now());
        return events
                .stream()
                .map(eventMapper::toDto)
                .toList();
    }

    private Customer getAuthenticatedCustomer() {
        var user = authService.getCurrentUser();
        var customer = customerRepository.findById(user.getId()).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        return customer;
    }
}
