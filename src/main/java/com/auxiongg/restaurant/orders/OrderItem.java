package com.auxiongg.restaurant.orders;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import com.auxiongg.restaurant.menu.MenuItem;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private MenuItem menuItem;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "total_Price")
    private BigDecimal totalPrice;

    public OrderItem(Order order, MenuItem menuItem, Integer quantity) {
        this.order = order;
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.unitPrice = menuItem.getPrice();
        this.totalPrice = menuItem.getPrice().multiply(new BigDecimal(quantity));
    }

    public BigDecimal calculateTotalPrice() {
        return menuItem.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

}