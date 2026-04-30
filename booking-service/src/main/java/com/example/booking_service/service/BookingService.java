package com.example.booking_service.service;

import com.example.booking_service.dto.BookingRequest;
import com.example.booking_service.dto.BookingResponse;
import com.example.booking_service.entity.Booking;
import com.example.booking_service.exception.SeatAlreadyLockedException;
import com.example.booking_service.kafka.BookingProducer;
import com.example.booking_service.redis.SeatLockService;
import com.example.booking_service.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repository;

    @Autowired
    private SeatLockService seatLockService;

    @Autowired
    private BookingProducer producer;

    public BookingResponse createBooking(BookingRequest request) {

        String seatKey = "lock:show:" + request.getShowId() + ":seat:" + request.getSeatNumber();

        // 🔒 Seat Lock Check
        if (!seatLockService.lockSeat(seatKey)) {
            throw new SeatAlreadyLockedException("Seat already locked");
        }

        // 💾 Save Booking
        Booking booking = new Booking();
        booking.setUserId(request.getUserId());
        booking.setShowId(request.getShowId());
        booking.setSeatNumber(request.getSeatNumber());
        booking.setStatus("PENDING");

        repository.save(booking);

        // 📡 Saga Start
        producer.sendBookingCreated(booking.getId());

        return new BookingResponse(booking.getId(), "PENDING");
    }
}