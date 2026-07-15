package com.auxiongg.restaurant.users.customers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import com.auxiongg.restaurant.events.Event;
import com.auxiongg.restaurant.orders.Order;
import com.auxiongg.restaurant.users.User;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "id")
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "street_number")
    private String houseNumber;

    @Column(name = "street")
    private String street;

    @Column(name = "postcode")
    private String postcode;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "customer_events",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> events = new HashSet<>();

    public void addEvent(Event event) {
        events.add(event);
    }

}