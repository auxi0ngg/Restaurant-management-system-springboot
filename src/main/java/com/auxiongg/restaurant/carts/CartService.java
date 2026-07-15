package com.auxiongg.restaurant.carts;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import com.auxiongg.restaurant.carts.dtos.CartDto;
import com.auxiongg.restaurant.carts.dtos.CartItemDto;
import com.auxiongg.restaurant.carts.dtos.CreateCartResponse;
import com.auxiongg.restaurant.carts.exceptions.CartNotFoundException;
import com.auxiongg.restaurant.menu.Category;
import com.auxiongg.restaurant.menu.MenuItemRepository;
import com.auxiongg.restaurant.menu.exceptions.MenuItemNotFoundException;
import com.auxiongg.restaurant.orders.exceptions.OrderNotFoundException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {
    private CartRepository cartRepository;
    private MenuItemRepository itemRepository;
    private CartMapper mapper;

    public CreateCartResponse createCart() {
        var cart = new Cart();
        cart = cartRepository.save(cart);
        var special = itemRepository.findByCategory(Category.SPECIAL).orElse(null);
        if (special == null) {
            throw new MenuItemNotFoundException();
        }
        return new CreateCartResponse(cart.getId(), special);
    }

    public CartItemDto addCartItem(Long itemId, UUID id) {
        var cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }

        var item = itemRepository.findById(itemId).orElse(null);
        if (item == null) {
            throw new MenuItemNotFoundException();
        }

        var cartItem = cart.addItem(item);

        cartRepository.save(cart);

        return mapper.toCartItemDto(cartItem);
    }

    public CartDto getCart(UUID id) {
        var cart = cartRepository.findById(id).orElse(null);
        if (cart == null) {
            throw new OrderNotFoundException();
        }

        return mapper.toCartDto(cart);
    }

    public CartItemDto updateCartItem(Integer quantity, UUID cartId, Long itemId) {
        var cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }

        var item = cart.getItem(itemId);

        if (cart.getItem(itemId) == null) {
            throw new MenuItemNotFoundException();
        }

        item.setQuantity(quantity);
        cartRepository.save(cart);
        return mapper.toCartItemDto(item);
    }

    public void deleteCartItem(UUID cartId, Long itemId) {
        var cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }

        cart.removeItem(itemId);
        cartRepository.save(cart);
    }

    public void clearCart(UUID cartId) {
        var cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        cart.clearCart();
        cartRepository.save(cart);
    }
}
