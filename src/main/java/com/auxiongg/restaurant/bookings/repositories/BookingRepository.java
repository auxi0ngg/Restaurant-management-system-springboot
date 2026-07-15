package com.auxiongg.restaurant.bookings.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.auxiongg.restaurant.bookings.entities.Booking;

import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("SELECT SUM(b.guests) FROM Booking b WHERE b.bookingDate = CURRENT_DATE()")
    Integer getNumberOfGuests();

    @Query("SELECT COUNT(*) FROM Booking b WHERE b.bookingDate = CURRENT_DATE()")
    Integer getNumberOfTables();

    Boolean existsByCustomerIdAndBookingDate(Long customerId, LocalDate bookingDate);
  }