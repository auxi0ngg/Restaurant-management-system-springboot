package com.auxiongg.restaurant.orders;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.auxiongg.restaurant.auth.AuthService;
import com.auxiongg.restaurant.carts.Cart;
import com.auxiongg.restaurant.carts.CartRepository;
import com.auxiongg.restaurant.carts.CartService;
import com.auxiongg.restaurant.carts.exceptions.CartIsEmptyException;
import com.auxiongg.restaurant.carts.exceptions.CartNotFoundException;
import com.auxiongg.restaurant.orders.dtos.*;
import com.auxiongg.restaurant.orders.exceptions.OrderNotFoundException;
import com.auxiongg.restaurant.users.customers.Customer;
import com.auxiongg.restaurant.users.customers.CustomerNotFoundException;
import com.auxiongg.restaurant.users.customers.CustomerRepository;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final AuthService authService;
    private final CartService cartService;

    public List<SimpleOrderDto> getAllOrders() {
        var orders = orderRepository.findAll();
        return orders
                .stream().map(orderMapper::toSimpleOrderDto)
                .toList();
    }

    public List<SimpleOrderDto> getAllRestaurantOrders() {
        var orders = orderRepository.findByOrderType(OrderType.RESTAURANT);
        return orders
                .stream()
                .map(orderMapper::toSimpleOrderDto)
                .toList();
    }

    public List<SimpleOrderDto> getAllTakeoutOrders() {
        var orders = orderRepository.findByOrderType(OrderType.TAKEOUT);
        return orders
                .stream()
                .map(orderMapper::toSimpleOrderDto)
                .toList();
    }

    public List<SimpleOrderDto> getAllDeliveryOrders() {
        var orders = orderRepository.findByOrderType(OrderType.DELIVERY);
        return orders
                .stream()
                .map(orderMapper::toSimpleOrderDto)
                .toList();
    }


    public List<SimpleOrderDto> getOutstandingOrders() {
        var orders = orderRepository.findByOrderStatus(OrderStatus.APPROVED);
        return orders
                .stream()
                .map(orderMapper::toSimpleOrderDto)
                .toList();
    }

    public DetailedOrderDto getOrder(Long id) {
        var order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            throw new OrderNotFoundException();
        }

        return orderMapper.toDetailedOrderDto(order);
    }



    public void approve(Long id) {
        var order = orderRepository.findByIdAndOrderStatus(id, OrderStatus.PENDING).orElse(null);
        if (order == null) {
            throw new OrderNotFoundException();
        }

        order.setOrderStatus(OrderStatus.APPROVED);

        orderRepository.save(order);
    }

    public void complete(Long id) {
        var order = orderRepository.findByIdAndOrderStatus(id, OrderStatus.APPROVED).orElse(null);
        if (order == null) {
            throw new OrderNotFoundException();
        }
        order.setOrderStatus(OrderStatus.COMPLETED);

        orderRepository.save(order);
    }


    public OrderDto createRestaurantOrder(UUID cartId) {
        var cart = getValidatedCart(cartId);
        var customer = getCurrentCustomer();

        var order = RestaurantOrder.fromCart(cart, customer);
        processOrder(order, cartId);
        return orderMapper.toRestaurantOrderDto(order);
    }

    public OrderDto createTakeoutOrder(UUID cartId, LocalTime pickupTime) {
        var cart = getValidatedCart(cartId);
        var customer = getCurrentCustomer();

        var order = TakeoutOrder.fromCart(cart, customer, pickupTime);
        processOrder(order, cartId);
        return orderMapper.toTakeoutOrderDto(order);
    }

    public OrderDto createDeliveryOrder(UUID cartId, LocalTime deliveryTime) {
        var cart = getValidatedCart(cartId);
        var customer = getCurrentCustomer();

        var order = DeliveryOrder.fromCart(cart, customer, deliveryTime);
        processOrder(order, cartId);
        return orderMapper.toDeliveryOrderDto(order);
    }

    private Cart getValidatedCart(UUID cartId) {
        var cart = cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
        if (cart.isEmpty()) {
            throw new CartIsEmptyException();
        }
        return cart;
    }

    private Customer getCurrentCustomer() {
        var user = authService.getCurrentUser();
        return customerRepository.findById(user.getId()).orElseThrow(CustomerNotFoundException::new);
    }

    private void processOrder(Order order, UUID cartId) {
        order.setOrderStatus(OrderStatus.PENDING);
        orderRepository.save(order);
        cartService.clearCart(cartId);
    }

}


