package com.auxiongg.restaurant.carts;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import com.auxiongg.restaurant.menu.MenuItem;

@Getter
@Setter
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDate createdAt;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<CartItem> items = new LinkedHashSet<>();

    public CartItem getItem(Long itemId) {
        for (var item : items) {
            if (item.getMenuItem().getId().equals(itemId)) {
                return item;
            }
        }
        return null;
    }

    public CartItem addItem(MenuItem item) {
        var cartItem = this.getItem(item.getId());
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        } else {
            cartItem = new CartItem();
            cartItem.setMenuItem(item);
            cartItem.setQuantity(1);
            cartItem.setCart(this);
            this.items.add(cartItem);
        }
        return cartItem;
    }

    public void removeItem(Long itemId) {
        var cartItem = this.getItem(itemId);
        if (cartItem != null) {
            items.remove(cartItem);
        }
    }

    public void clearCart() {
        this.getItems().clear();
    }

    public boolean isEmpty() {
        return this.getItems().isEmpty();
    }


    public BigDecimal calculateTotalPrice() {
        return items.stream()
                .map(CartItem::calculateTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}