package com.example.demo.MockRepo;

import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// In-memory mock implementation of the BookingRepository
@Repository
@Profile("mock")
public class InMemoryBookingRepository implements BookingRepository {

    private final Map<Integer, Booking> bookingMap = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    @Override
    public Booking save(Booking booking) {
        booking.setId(idGenerator.getAndIncrement());
        bookingMap.put(booking.getId(), booking);
        return booking;
    }

    @Override
    public List<Booking> findAll() {
        return new ArrayList<>(bookingMap.values());
    }

    @Override
    public Booking findById(int id) {
        return bookingMap.get(id);
    }

    @Override
    public Booking update(int id, Booking updatedBooking) {
        if (bookingMap.containsKey(id)) {
            updatedBooking.setId(id);
            bookingMap.put(id, updatedBooking);
            return updatedBooking;
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return bookingMap.remove(id) != null;
    }
}