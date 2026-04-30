package com.example.payment_service.kafka;

import com.example.payment_service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentProducer producer;

    @KafkaListener(topics = "booking-created", groupId = "payment-group")
    public void consume(String bookingIdStr) {

        Long bookingId = Long.parseLong(bookingIdStr);

        System.out.println("Processing payment for booking: " + bookingId);

        boolean success = paymentService.processPayment(bookingId);

        if (success) {
            producer.sendSuccess(bookingId);
        } else {
            producer.sendFailure(bookingId);
        }
    }
}