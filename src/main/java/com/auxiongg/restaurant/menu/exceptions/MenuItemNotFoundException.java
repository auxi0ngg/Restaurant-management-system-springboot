package com.auxiongg.restaurant.menu.exceptions;

public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException() {
        super("MenuItem not found");
    }
}
