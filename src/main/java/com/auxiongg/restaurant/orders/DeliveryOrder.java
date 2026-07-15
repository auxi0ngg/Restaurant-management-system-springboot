package com.auxiongg.restaurant.orders;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

import com.auxiongg.restaurant.carts.Cart;
import com.auxiongg.restaurant.users.customers.Customer;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "DELIVERY")
public class DeliveryOrder extends Order {

    @Column(name = "delivery_time")
    private LocalTime deliveryTime;

    public static DeliveryOrder fromCart(Cart cart, Customer customer, LocalTime deliveryTime) {
        var order = new DeliveryOrder();
        order.setCustomer(customer);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setDeliveryTime(deliveryTime);
        order.setTotalPrice(cart.calculateTotalPrice());

        cart.getItems().forEach(item -> {
            var orderItem = new OrderItem(order, item.getMenuItem(), item.getQuantity());
            order.getOrderItems().add(orderItem);
        });
        return order;
    }

}