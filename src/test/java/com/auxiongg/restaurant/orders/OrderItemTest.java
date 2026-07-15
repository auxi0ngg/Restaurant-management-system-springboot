package com.auxiongg.restaurant.orders;

import com.auxiongg.restaurant.menu.MenuItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderItemTest {

    @Test
    void constructor_shouldPopulateUnitPriceAndTotalPrice() {
        MenuItem menuItem = new MenuItem();
        menuItem.setPrice(new BigDecimal("10.50"));
        Order order = new RestaurantOrder();

        OrderItem orderItem = new OrderItem(order, menuItem, 3);

        assertEquals(order, orderItem.getOrder());
        assertEquals(menuItem, orderItem.getMenuItem());
        assertEquals(3, orderItem.getQuantity());
        assertEquals(new BigDecimal("10.50"), orderItem.getUnitPrice());
        assertEquals(new BigDecimal("31.50"), orderItem.getTotalPrice());
    }

    @Test
    void calculateTotalPrice_shouldMultiplyPriceByQuantity() {
        MenuItem menuItem = new MenuItem();
        menuItem.setPrice(new BigDecimal("4.25"));
        OrderItem orderItem = new OrderItem(new RestaurantOrder(), menuItem, 4);

        assertEquals(new BigDecimal("17.00"), orderItem.calculateTotalPrice());
    }
}
