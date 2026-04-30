package com.example.booking_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"showId", "seatNumber"})
)
public class Booking {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;
    private Long showId;
    private String seatNumber;

    private String status; // PENDING, CONFIRMED, FAILED
}