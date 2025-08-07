package com.example.demo.repository;

import com.example.demo.model.Booking;
import java.util.List;

// Interface defining basic CRUD operations for bookings
public interface BookingRepository {

    Booking save(Booking booking);

    List<Booking> findAll();

    Booking findById(int id);

    Booking update(int id, Booking booking);

    boolean delete(int id);
}
