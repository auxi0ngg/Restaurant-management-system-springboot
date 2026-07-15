package com.auxiongg.restaurant.orders;

import com.auxiongg.restaurant.carts.Cart;
import com.auxiongg.restaurant.carts.CartItem;
import com.auxiongg.restaurant.menu.MenuItem;
import com.auxiongg.restaurant.users.customers.Customer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderFactoryTest {

    @Test
    void restaurantOrderFromCart_shouldPopulateOrderAndItems() {
        Cart cart = new Cart();
        MenuItem menuItem = new MenuItem();
        menuItem.setId(1L);
        menuItem.setPrice(new BigDecimal("12.00"));
        cart.addItem(menuItem);

        Customer customer = new Customer();
        customer.setId(7L);

        RestaurantOrder order = RestaurantOrder.fromCart(cart, customer);

        assertEquals(customer, order.getCustomer());
        assertEquals(OrderStatus.PENDING, order.getOrderStatus());
        assertEquals(new BigDecimal("12.00"), order.getTotalPrice());
        assertEquals(1, order.getOrderItems().size());
        assertEquals(menuItem, order.getOrderItems().iterator().next().getMenuItem());
        assertEquals(1, order.getOrderItems().iterator().next().getQuantity());
    }

    @Test
    void deliveryOrderFromCart_shouldSetDeliveryTimeAndOrderItems() {
        Cart cart = new Cart();
        MenuItem menuItem = new MenuItem();
        menuItem.setId(2L);
        menuItem.setPrice(new BigDecimal("8.00"));
        cart.addItem(menuItem);

        Customer customer = new Customer();
        LocalTime deliveryTime = LocalTime.of(19, 30);

        DeliveryOrder order = DeliveryOrder.fromCart(cart, customer, deliveryTime);

        assertEquals(customer, order.getCustomer());
        assertEquals(OrderStatus.PENDING, order.getOrderStatus());
        assertEquals(deliveryTime, order.getDeliveryTime());
        assertEquals(new BigDecimal("8.00"), order.getTotalPrice());
        assertEquals(1, order.getOrderItems().size());
    }

    @Test
    void takeoutOrderFromCart_shouldSetPickupTimeAndOrderItems() {
        Cart cart = new Cart();
        MenuItem menuItem = new MenuItem();
        menuItem.setId(3L);
        menuItem.setPrice(new BigDecimal("6.50"));
        cart.addItem(menuItem);

        Customer customer = new Customer();
        LocalTime pickupTime = LocalTime.of(18, 15);

        TakeoutOrder order = TakeoutOrder.fromCart(cart, customer, pickupTime);

        assertEquals(customer, order.getCustomer());
        assertEquals(OrderStatus.PENDING, order.getOrderStatus());
        assertEquals(pickupTime, order.getPickupTime());
        assertEquals(new BigDecimal("6.50"), order.getTotalPrice());
        assertEquals(1, order.getOrderItems().size());
    }
}
