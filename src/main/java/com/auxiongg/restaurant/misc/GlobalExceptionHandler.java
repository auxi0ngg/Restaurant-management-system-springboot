package com.auxiongg.restaurant.misc;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.auxiongg.restaurant.bookings.exceptions.*;
import com.auxiongg.restaurant.carts.exceptions.CartIsEmptyException;
import com.auxiongg.restaurant.carts.exceptions.CartNotFoundException;
import com.auxiongg.restaurant.events.exceptions.*;
import com.auxiongg.restaurant.menu.exceptions.MenuItemNotFoundException;
import com.auxiongg.restaurant.orders.exceptions.OrderAlreadyPlacedException;
import com.auxiongg.restaurant.orders.exceptions.OrderNotFoundException;
import com.auxiongg.restaurant.users.UserNotFoundException;
import com.auxiongg.restaurant.users.customers.CustomerNotFoundException;
import com.auxiongg.restaurant.users.staff.StaffNotFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handleUnreadableMessage() {
        return ResponseEntity.badRequest().body(
                new ErrorDto("Invalid request body")
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDto> handleBadCredentialException(Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDto(e.getMessage()));
    }

    @ExceptionHandler({
            CustomerNotFoundException.class,
            UserNotFoundException.class,
            StaffNotFoundException.class,
            OrderNotFoundException.class,
            MenuItemNotFoundException.class,
            CartNotFoundException.class,
            EventNotFoundException.class,
            NoGuestsFoundException.class,
            TimeSlotNotFoundException.class,
            BookingNotFoundException.class,
    })
    public ResponseEntity<ErrorDto> handleNotFoundException(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDto(e.getMessage()));
    }

    @ExceptionHandler({
            CartIsEmptyException.class,
            OrderAlreadyPlacedException.class,
            EventFullyBookedException.class,
            EventAlreadyApprovedException.class,
            EventComplete.class,
            SameDayEventException.class,
            TableNotAvailableException.class,
            MultipleBookingsException.class
    })
    public ResponseEntity<ErrorDto> handleBadRequest(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDto(e.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDto> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = "Duplicate entry or constraint violation.";

        if (ex.getRootCause() != null && ex.getRootCause().getMessage().contains("Duplicate entry")) {
            message = "Duplicate entry: the record already exists.";
        }

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorDto(message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationErrors(
            MethodArgumentNotValidException exception) {

        var errors = new HashMap<String, String>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

    }

}
