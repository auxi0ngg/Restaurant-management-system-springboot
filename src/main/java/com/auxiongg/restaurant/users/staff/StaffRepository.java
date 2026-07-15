package com.auxiongg.restaurant.users.staff;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    @Query("SELECT s, COUNT(sh)" +
            " FROM Staff s " +
            "JOIN s.shifts sh " +
            "WHERE sh.date >= :sevenDaysAgo" +
            " GROUP BY s " +
            "ORDER BY COUNT(sh) desc")
    List<Staff> findMostActiveStaff(Pageable pageable, @Param("sevenDaysAgo") LocalDate sevenDaysAgo);
}