package com.auxiongg.restaurant.bookings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.auxiongg.restaurant.bookings.entities.RestaurantTable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TableRepository extends JpaRepository<RestaurantTable, Long> {
    @Procedure("findAvailableTables")
    Optional<List<RestaurantTable>> findAvailableTables(LocalTime startTime, LocalTime endTime, LocalDate bookingDate);


    @Procedure("findTableForBooking")
    Optional<RestaurantTable> findTableForBooking(LocalDate bookingDate, LocalTime startTime, LocalTime endTime, Integer guests);
  }