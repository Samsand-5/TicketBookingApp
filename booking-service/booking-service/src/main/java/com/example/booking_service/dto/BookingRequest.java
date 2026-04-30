package com.example.booking_service.dto;

import lombok.Data;

@Data
public class BookingRequest {
    private Long userId;
    private Long showId;
    private String seatNumber;
}
