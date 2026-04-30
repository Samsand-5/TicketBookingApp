package com.example.booking_service.kafka;

import com.example.booking_service.entity.Booking;
import com.example.booking_service.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentConsumer {

    @Autowired
    private BookingRepository repository;

    // Handle SUCCESS
    @KafkaListener(topics = "payment-success", groupId = "booking-group")
    public void handlePaymentSuccess(String bookingIdStr) {

        Long bookingId = Long.parseLong(bookingIdStr);

        Optional<Booking> optionalBooking = repository.findById(bookingId);

        if (optionalBooking.isPresent()) {

            Booking booking = optionalBooking.get();

            // Idempotency check
            if (!"CONFIRMED".equals(booking.getStatus())) {
                booking.setStatus("CONFIRMED");
                repository.save(booking);

                System.out.println("Booking CONFIRMED: " + bookingId);
            }

        } else {
            System.out.println("Booking not found for ID: " + bookingId);
        }
    }

    // Handle FAILURE
    @KafkaListener(topics = "payment-failed", groupId = "booking-group")
    public void handlePaymentFailure(String bookingIdStr) {

        Long bookingId = Long.parseLong(bookingIdStr);

        Optional<Booking> optionalBooking = repository.findById(bookingId);

        if (optionalBooking.isPresent()) {

            Booking booking = optionalBooking.get();

            // Idempotency check
            if (!"FAILED".equals(booking.getStatus())) {
                booking.setStatus("FAILED");
                repository.save(booking);

                System.out.println("Booking FAILED: " + bookingId);
            }

        } else {
            System.out.println("Booking not found for ID: " + bookingId);
        }
    }
}