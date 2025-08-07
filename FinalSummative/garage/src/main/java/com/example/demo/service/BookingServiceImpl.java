package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repo;

    @Autowired
    public BookingServiceImpl(BookingRepository repo) {
        this.repo = repo;
    }

    @Override
    public Booking addBooking(Booking booking) {
        return repo.save(booking);
    }

    @Override
    public List<Booking> getAllBookings() {
        return repo.findAll();
    }

    @Override
    public Booking getBookingById(int id) {
        return repo.findById(id);
    }

    @Override
    public Booking updateBooking(int id, Booking updatedBooking) {
        return repo.update(id, updatedBooking);
    }

    @Override
    public boolean deleteBooking(int id) {
        return repo.delete(id);
    }
}
