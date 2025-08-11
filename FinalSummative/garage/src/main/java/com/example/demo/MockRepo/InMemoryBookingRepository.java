package com.example.demo.MockRepo;

import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// In-memory mock implementation of the BookingRepository
@Repository
@Profile("mock")
public class InMemoryBookingRepository implements BookingRepository {

    private final Map<Integer, Booking> bookingMap = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    private void initializeSampleData() {
        // Example bookings: customerId, mechanicId, serviceId
        save(new Booking(0, 1, 1, 1, "Morning", new BigDecimal("39.99"))); // Nesha + John Smith + Oil Change
        save(new Booking(0, 2, 2, 4, "Afternoon", new BigDecimal("89.99"))); // Elijah + Sarah Johnson + Engine Diagnostics
        save(new Booking(0, 3, 5, 7, "Morning", new BigDecimal("99.99"))); // Tina + David Wilson + A/C Repair
        save(new Booking(0, 4, 3, 6, "Afternoon", new BigDecimal("149.99"))); // Brandon + Mike Rodriguez + Transmission
        save(new Booking(0, 5, 4, 2, "Morning", new BigDecimal("59.99"))); // Sophia + Laura Chen + Brake Inspection
    }


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