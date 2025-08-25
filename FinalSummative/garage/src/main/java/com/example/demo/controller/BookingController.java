// src/main/java/com/example/demo/controller/BookingController.java
package com.example.demo.controller;

import com.example.demo.DataTransferObject.UpdateStatusRequest;
import com.example.demo.model.Booking;
import com.example.demo.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173") // optional, helps local dev
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // GET all bookings
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    // GET booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable int id) {
        Booking booking = bookingService.getBookingById(id);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }

    // POST a new booking
    @PostMapping
    public ResponseEntity<Booking> addBooking(@Valid @RequestBody Booking booking) {
        Booking saved = bookingService.addBooking(booking);
        return ResponseEntity
                .created(URI.create("/api/bookings/" + saved.getId()))
                .body(saved);
    }

    // PUT to update a booking
    @PutMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@PathVariable int id,
                                                 @Valid @RequestBody Booking updatedBooking) {
        Booking updated = bookingService.updateBooking(id, updatedBooking);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // DELETE a booking
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable int id) {
        boolean deleted = bookingService.deleteBooking(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable int id,
            @RequestBody @Valid UpdateStatusRequest body
    ) {
        Booking updated = bookingService.updateStatus(id, body.getStatus());
        return (updated == null) ? ResponseEntity.notFound().build()
                : ResponseEntity.noContent().build();
    }
}

