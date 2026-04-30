package com.example.booking_service.exception;

public class SeatAlreadyLockedException extends RuntimeException {

    public SeatAlreadyLockedException(String message) {
        super(message);
    }
}