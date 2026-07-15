package com.auxiongg.restaurant.users.customers;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c, COUNT(o)" +
            "FROM Customer c " +
            "JOIN c.orders o " +
            "WHERE o.createdAt >= :sevenDaysAgo" +
            " GROUP BY c " +
            "ORDER BY COUNT(o) desc")
    List<Customer> findMostActiveCustomersInLast7Days(Pageable pageable, @Param("sevenDaysAgo") LocalDateTime sevenDaysAgo);
}