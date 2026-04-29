package com.example.booking_service.kafka;

import com.example.booking_service.entity.Booking;
import com.example.booking_service.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    @Autowired
    private BookingRepository bookingRepository;

    @KafkaListener(topics = "payment-success")
    public void handleSuccess(String bookingId) {
        Booking booking = bookingRepository.findById(Long.parseLong(bookingId)).get();
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);
    }

    @KafkaListener(topics = "payment-failed")
    public void handleFailure(String bookingId) {
        Booking booking = bookingRepository.findById(Long.parseLong(bookingId)).get();
        booking.setStatus("FAILED");
        bookingRepository.save(booking);
    }
}