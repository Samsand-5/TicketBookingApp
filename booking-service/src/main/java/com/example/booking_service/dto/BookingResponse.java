package com.example.booking_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookingResponse {
    private Long bookingId;
    private String status;
}