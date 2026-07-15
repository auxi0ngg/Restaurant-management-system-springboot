package com.auxiongg.restaurant.carts;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.auxiongg.restaurant.carts.dtos.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/carts")
@Tag(name = "Carts")
public class CartController {
    private final CartService cartService;

    @GetMapping("/{id}")
    @Operation(summary = "Any user can view a single cart")
    public ResponseEntity<CartDto> getCart(@PathVariable UUID id) {
        var cartDto = cartService.getCart(id);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Any user can create a cart. " +
            "Daily specials will be displayed in the response")
    public ResponseEntity<CreateCartResponse> createCart() {
        var cartDto = cartService.createCart();

        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);

    }

    @PostMapping("/{id}/items")
    @Operation(summary = "Any user can add an menu item to a cart")
    public ResponseEntity<CartItemDto> addItemToCart(
            @PathVariable UUID id,
            @Valid @RequestBody AddItemToCartRequest request
    ) {
        var cartItemDto = cartService.addCartItem(request.getItemId(), id);
        return new ResponseEntity<>(cartItemDto, HttpStatus.CREATED);
    }

    @PutMapping("/{cartId}/items/{itemId}")
    @Operation(summary = "Any user can update a menu item in a cart")
    public ResponseEntity<CartItemDto> updateCartItem(
            @Valid @RequestBody UpdateItemInCartRequest request,
            @PathVariable UUID cartId,
            @PathVariable Long itemId
    ) {
       var cartItemDto = cartService.updateCartItem(request.getQuantity(), cartId, itemId);
       return new ResponseEntity<>(cartItemDto, HttpStatus.OK);
    }

    @DeleteMapping("/{cartId}/items/{itemId}")
    @Operation(summary = "Any user can remove a menu item from a cart")
    public ResponseEntity<Void> deleteCartItem(
            @PathVariable UUID cartId,
            @PathVariable Long itemId) {

        cartService.deleteCartItem(cartId, itemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/{cartId}/items")
    @Operation(summary = "Any user can clear a cart")
    public ResponseEntity<Void> clearCart(@PathVariable UUID cartId) {
        cartService.clearCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
