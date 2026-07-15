package com.auxiongg.restaurant.users.customers;

import lombok.Data;

import java.util.List;
import java.util.Set;

import com.auxiongg.restaurant.events.EventDto;
import com.auxiongg.restaurant.orders.dtos.SimpleOrderDto;

@Data
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String houseNumber;
    private String street;
    private String postcode;
    private List<SimpleOrderDto> orders;
    private Set<EventDto> events;
}
