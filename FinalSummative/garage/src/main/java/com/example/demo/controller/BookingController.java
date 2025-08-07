package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // GET all bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // GET booking by ID
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable int id) {
        return bookingService.getBookingById(id);
    }

    // POST a new booking
    @PostMapping
    public Booking addBooking(@RequestBody Booking booking) {
        return bookingService.addBooking(booking);
    }

    // PUT to update a booking
    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable int id, @RequestBody Booking updatedBooking) {
        return bookingService.updateBooking(id, updatedBooking);
    }

    // DELETE a booking
    @DeleteMapping("/{id}")
    public boolean deleteBooking(@PathVariable int id) {
        return bookingService.deleteBooking(id);
    }
}
