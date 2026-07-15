package com.auxiongg.restaurant.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderStatus(OrderStatus orderStatus);

    Optional<Order> findByIdAndOrderStatus(Long orderId, OrderStatus orderStatus);

    List<Order> findByOrderType(OrderType orderType);

}