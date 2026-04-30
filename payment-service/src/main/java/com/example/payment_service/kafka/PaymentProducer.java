package com.example.payment_service.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendSuccess(Long bookingId) {
        kafkaTemplate.send("payment-success", bookingId.toString());
    }

    public void sendFailure(Long bookingId) {
        kafkaTemplate.send("payment-failed", bookingId.toString());
    }
}