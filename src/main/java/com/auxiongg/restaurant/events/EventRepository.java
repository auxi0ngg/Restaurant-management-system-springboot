package com.auxiongg.restaurant.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.auxiongg.restaurant.users.customers.Customer;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query("SELECT e FROM Event e WHERE e.status = :status AND e.date > :date")
    List<Event> findAllByStatusAndAfterToday(@Param("status") EventStatus status, @Param("date") LocalDate date);

    Boolean existsByDateAndCustomer(LocalDate date, Customer customer);
}