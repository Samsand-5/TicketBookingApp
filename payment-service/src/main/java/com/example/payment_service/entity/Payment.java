package com.example.payment_service.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    private Long bookingId;

    private String status; // SUCCESS / FAILED
}