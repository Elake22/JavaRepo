package com.example.demo.service;

import com.example.demo.model.Booking;
import java.util.List;

public interface BookingService {

    Booking addBooking(Booking booking);

    List<Booking> getAllBookings();

    Booking getBookingById(int id);

    Booking updateBooking(int id, Booking updatedBooking);

    boolean deleteBooking(int id);
}
