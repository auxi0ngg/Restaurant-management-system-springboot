package com.auxiongg.restaurant.events.exceptions;

public class EventComplete extends RuntimeException {
    public EventComplete() {
        super("Event complete");
    }
}
