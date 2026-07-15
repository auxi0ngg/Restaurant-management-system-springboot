package com.auxiongg.restaurant.orders;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.auxiongg.restaurant.orders.dtos.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
@Tag(name = "Orders")
class OrderController {
    private final OrderService orderService;

    @PostMapping("/restaurant")
    @PreAuthorize("hasRole('WAITER')")
    @Operation(summary = "A waiter can create a restaurant order")
    public ResponseEntity<?> createRestaurantOrder(
            @Valid @RequestBody CreateRestaurantOrderRequest request) {

        var orderDto = orderService.createRestaurantOrder(request.getCartId());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }

    @PostMapping("/takeout")
    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(summary = "A customer can create a takeout order")
    public ResponseEntity<?> createTakeoutOrder(
            @Valid @RequestBody CreateTakeoutOrderRequest request) {

        var orderDto = orderService.createTakeoutOrder(request.getCartId(), request.getPickupTime());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }


    @PostMapping("/delivery")
    @PreAuthorize("hasRole('CUSTOMER')")
    @Operation(summary = "A customer can create a delivery order")
    public ResponseEntity<?> createDeliveryOrder(
            @Valid @RequestBody CreateDeliveryOrderRequest request) {

        var orderDto = orderService.createDeliveryOrder(request.getCartId(), request.getDeliveryTime());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }

    @PreAuthorize("hasAnyRole('WAITER', 'CHEF', 'MANAGER', 'DELIVERY_DRIVER')")
    @GetMapping("/restaurant")
    @Operation(summary = "A staff member can view all restaurant orders")
    public ResponseEntity<List<SimpleOrderDto>> getAllRestaurantOrders() {
        return ResponseEntity.ok(orderService.getAllRestaurantOrders());
    }

    @PreAuthorize("hasAnyRole('WAITER', 'CHEF', 'MANAGER', 'DELIVERY_DRIVER')")
    @GetMapping("/takeout")
    @Operation(summary = "A staff member can view all takeout orders")
    public ResponseEntity<List<SimpleOrderDto>> getAllTakeoutOrders() {
        return ResponseEntity.ok(orderService.getAllTakeoutOrders());
    }

    @PreAuthorize("hasAnyRole('WAITER', 'CHEF', 'MANAGER', 'DELIVERY_DRIVER')")
    @GetMapping("/delivery")
    @Operation(summary = "A staff member can view all delivery orders")
    public ResponseEntity<List<SimpleOrderDto>> getAllDeliveryOrders() {
        return ResponseEntity.ok(orderService.getAllDeliveryOrders());
    }

    @PreAuthorize("hasAnyRole('WAITER', 'CHEF', 'MANAGER', 'WAITER')")
    @GetMapping
    @Operation(summary = "A staff member can view all orders")
    public ResponseEntity<List<SimpleOrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PreAuthorize("hasAnyRole('WAITER', 'CHEF', 'MANAGER', 'DELIVERY_DRIVER')")
    @GetMapping("/{id}")
    @Operation(summary = "A staff member can get a single order")
    public ResponseEntity<DetailedOrderDto> getOrder(@PathVariable Long id) {
        var orderDto = orderService.getOrder(id);
        return ResponseEntity.ok(orderDto);
    }

    @PreAuthorize("hasAnyRole('WAITER', 'CHEF', 'MANAGER', 'DELIVERY_DRIVER')")
    @GetMapping("/outstanding")
    @Operation(summary = "A staff member can get all orders that have been approved but are not complete")
    public ResponseEntity<List<SimpleOrderDto>> getOutstandingOrders() {
        return ResponseEntity.ok(orderService.getOutstandingOrders());
    }

    @PreAuthorize("hasRole('WAITER')")
    @PostMapping("/{id}/approve")
    @Operation(summary = "A waiter can mark an order as approved")
    public ResponseEntity<Void> approve(@PathVariable Long id) {
        orderService.approve(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('CHEF')")
    @PostMapping("/{id}/complete")
    @Operation(summary = "A chef can mark an order as complete")
    public ResponseEntity<Void> complete(@PathVariable Long id) {
        orderService.complete(id);
        return ResponseEntity.ok().build();
    }

}
