package com.auxiongg.restaurant.users.customers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.auxiongg.restaurant.auth.AuthService;
import com.auxiongg.restaurant.events.*;
import com.auxiongg.restaurant.events.exceptions.EventFullyBookedException;
import com.auxiongg.restaurant.events.exceptions.EventNotFoundException;
import com.auxiongg.restaurant.users.UserNotFoundException;

@AllArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AuthService authService;
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;


    public CustomerDto createCustomerProfile(CreateCustomerProfileRequest request) {
        var customer = customerMapper.toEntity(request);
        var user = authService.getCurrentUser();
        if (user == null) {
            throw new UserNotFoundException();
        }

        customer.setUser(user);
        customerRepository.save(customer);
        return customerMapper.toDto(customer);
    }

    public CustomerDto me() {
        var user = authService.getCurrentUser();
        if (user == null) {
            throw new UserNotFoundException();
        }
        var customer = customerRepository.findById(user.getId()).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        return customerMapper.toDto(customer);
    }

    public EventDto addEvent(Long eventId)  {
        var user = authService.getCurrentUser();
        var customer = customerRepository.findById(user.getId()).orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException();
        }
        var event = eventRepository.findById(eventId).orElse(null);
        if (event == null) {
            throw new EventNotFoundException();
        }

        if (event.isFullyBooked()) {
            throw new EventFullyBookedException();
        }

        customer.addEvent(event);
        customerRepository.save(customer);
        return eventMapper.toDto(event);

    }
}
