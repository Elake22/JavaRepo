package com.example.demo.service;

import com.example.demo.repository.BookingRepository;

public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking createBooking(Booking booking) {
        return null;
    }

    @Override
    public List<Booking> getAllBookings() {
        return null;
    }

    @Override
    public Booking getBookingById(int id) {
        return null;
    }

    // implement methods here
}

