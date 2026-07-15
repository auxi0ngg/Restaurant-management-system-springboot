package com.auxiongg.restaurant.bookings.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.auxiongg.restaurant.bookings.entities.TimeSlot;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    Optional<TimeSlot> findByStartTime(LocalTime startTime);

    @Query("SELECT t, COUNT(b) FROM TimeSlot t JOIN t.bookings b WHERE b.createdAt >= :sevenDaysAgo GROUP BY t ORDER BY COUNT(b) desc")
    List<TimeSlot> getBusiestPeriods(Pageable pageable, @Param("sevenDaysAgo") LocalDateTime sevenDaysAgo);


}
