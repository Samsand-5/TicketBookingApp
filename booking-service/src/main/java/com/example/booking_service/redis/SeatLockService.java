package com.example.booking_service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class SeatLockService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean lockSeat(String seatKey) {
        return Boolean.TRUE.equals(
                redisTemplate.opsForValue()
                        .setIfAbsent(seatKey, "LOCKED", Duration.ofMinutes(5))
        );
    }
}