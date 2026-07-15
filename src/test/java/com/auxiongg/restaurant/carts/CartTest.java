package com.auxiongg.restaurant.carts;

import com.auxiongg.restaurant.menu.Category;
import com.auxiongg.restaurant.menu.MenuItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    @Test
    void addItem_shouldCreateNewCartItem_whenItemIsNotPresent() {
        Cart cart = new Cart();
        MenuItem item = new MenuItem();
        item.setId(10L);
        item.setPrice(new BigDecimal("12.50"));

        CartItem savedItem = cart.addItem(item);

        assertEquals(1, cart.getItems().size());
        assertSame(item, savedItem.getMenuItem());
        assertEquals(1, savedItem.getQuantity());
        assertSame(cart, savedItem.getCart());
    }

    @Test
    void addItem_shouldIncreaseQuantity_whenSameItemAlreadyExists() {
        Cart cart = new Cart();
        MenuItem item = new MenuItem();
        item.setId(11L);
        item.setPrice(new BigDecimal("5.00"));

        cart.addItem(item);
        cart.addItem(item);

        assertEquals(1, cart.getItems().size());
        assertEquals(2, cart.getItem(11L).getQuantity());
    }

    @Test
    void removeItem_shouldDeleteItemFromCart() {
        Cart cart = new Cart();
        MenuItem item = new MenuItem();
        item.setId(12L);
        item.setPrice(new BigDecimal("7.00"));
        cart.addItem(item);

        cart.removeItem(12L);

        assertTrue(cart.isEmpty());
        assertNull(cart.getItem(12L));
    }

    @Test
    void clearCart_shouldRemoveAllItems() {
        Cart cart = new Cart();
        MenuItem item = new MenuItem();
        item.setId(13L);
        item.setPrice(new BigDecimal("9.00"));
        cart.addItem(item);

        cart.clearCart();

        assertTrue(cart.isEmpty());
        assertEquals(0, cart.getItems().size());
    }

    @Test
    void calculateTotalPrice_shouldReturnSumOfAllCartItems() {
        Cart cart = new Cart();
        MenuItem item1 = new MenuItem();
        item1.setId(14L);
        item1.setPrice(new BigDecimal("4.50"));
        MenuItem item2 = new MenuItem();
        item2.setId(15L);
        item2.setPrice(new BigDecimal("2.25"));

        cart.addItem(item1);
        cart.addItem(item2);
        cart.getItem(14L).setQuantity(2);
        cart.getItem(15L).setQuantity(3);

        assertEquals(new BigDecimal("4.50").multiply(BigDecimal.valueOf(2)).add(new BigDecimal("2.25").multiply(BigDecimal.valueOf(3))), cart.calculateTotalPrice());
    }
}
