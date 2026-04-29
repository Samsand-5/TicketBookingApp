package com.example.booking_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Booking {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private Long showId;
    private String seatNumber;

    private String status; // PENDING, CONFIRMED, FAILED
}