package com.example.booking_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendBookingCreated(Long bookingId) {
        kafkaTemplate.send("booking-created", bookingId.toString());
    }
}