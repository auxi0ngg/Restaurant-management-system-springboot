package com.auxiongg.restaurant.orders;

import com.auxiongg.restaurant.carts.Cart;
import com.auxiongg.restaurant.users.customers.Customer;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue(value = "RESTAURANT")
public class RestaurantOrder extends Order {
    public static RestaurantOrder fromCart(Cart cart, Customer customer) {

        var order = new RestaurantOrder();
        order.setCustomer(customer);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setTotalPrice(cart.calculateTotalPrice());

        cart.getItems().forEach(item -> {
          var orderItem = new OrderItem(order, item.getMenuItem(), item.getQuantity());
          order.getOrderItems().add(orderItem);
        });
        return order;
  }

}