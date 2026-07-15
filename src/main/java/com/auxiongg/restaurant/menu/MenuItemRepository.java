package com.auxiongg.restaurant.menu;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Optional<MenuItem> findByCategory(Category category);

    @Query("SELECT mi, SUM(oi.quantity) FROM MenuItem mi JOIN mi.orderItems oi WHERE oi.order.createdAt >= :sevenDaysAgo GROUP BY mi ORDER BY SUM(oi.quantity) desc")
    List<MenuItem> findMostPopularMenuItems(Pageable pageable, @Param("sevenDaysAgo") LocalDateTime sevenDaysAgo);

  }