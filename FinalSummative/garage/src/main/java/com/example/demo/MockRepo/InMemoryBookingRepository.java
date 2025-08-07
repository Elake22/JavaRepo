package com.example.demo.MockRepo;

import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;

import java.util.*;

// In-memory mock implementation of the BookingRepository
public class InMemoryBookingRepository implements BookingRepository {

    private final Map<Integer, Booking> bookingMap = new HashMap<>();
    private int currentId = 1;

    @Override
    public Booking save(Booking booking) {
        booking.setId(currentId++);
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
    public Booking update(int id, Booking booking) {
        if (bookingMap.containsKey(id)) {
            booking.setId(id);
            bookingMap.put(id, booking);
            return booking;
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return bookingMap.remove(id) != null;
    }
}
