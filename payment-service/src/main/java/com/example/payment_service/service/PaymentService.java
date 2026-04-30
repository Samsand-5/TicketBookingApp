package com.example.payment_service.service;

import com.example.payment_service.entity.Payment;
import com.example.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public boolean processPayment(Long bookingId) {

        boolean success = new Random().nextBoolean();

        Payment payment = new Payment();
        payment.setBookingId(bookingId);
        payment.setStatus(success ? "SUCCESS" : "FAILED");

        repository.save(payment);

        return success;
    }
}