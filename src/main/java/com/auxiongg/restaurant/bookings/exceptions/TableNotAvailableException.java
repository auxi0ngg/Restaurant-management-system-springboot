package com.auxiongg.restaurant.bookings.exceptions;

public class TableNotAvailableException extends RuntimeException {
    public TableNotAvailableException() {
        super("Table not available");
    }
}
