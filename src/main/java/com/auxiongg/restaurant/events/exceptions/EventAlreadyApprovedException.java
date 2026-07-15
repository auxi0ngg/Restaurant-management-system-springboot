package com.auxiongg.restaurant.events.exceptions;

public class EventAlreadyApprovedException extends RuntimeException {
    public EventAlreadyApprovedException() {
        super("Event already approved");
    }
}
