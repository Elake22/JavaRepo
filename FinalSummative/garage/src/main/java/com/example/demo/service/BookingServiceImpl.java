package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static java.lang.System.in;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repo;

    public BookingServiceImpl(BookingRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public Booking addBooking(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking is required.");
        }
        if (booking.getAppointmentAt() == null) {
            throw new IllegalArgumentException("appointmentAt is required.");
        }

        // Disallow past dates (date-only check, keeps morning/afternoon logic)
        LocalDate apptDate = booking.getAppointmentAt().toLocalDate();
        if (apptDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past.");
        }

        // Basic FK presence checks to avoid 500s from DB constraints
        if (booking.getCustomer() == null || booking.getCustomer().getId() == null) {
            throw new IllegalArgumentException("customer.id is required.");
        }
        if (booking.getService() == null || booking.getService().getId() == null) {
            throw new IllegalArgumentException("service.id is required.");
        }
        if (booking.getMechanic() == null || booking.getMechanic().getId() == null) {
            throw new IllegalArgumentException("mechanic.id is required.");
        }

        return repo.save(booking);
    }
    @Override
    public List<Booking> getAllBookings() {
        return repo.findAll();
    }

    @Override
    public Booking getBookingById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Booking updateBooking(int id, Booking updatedBooking) {
        return repo.findById(id).map(existing -> {
            // copy over fields you allow updates for
            existing.setCustomer(updatedBooking.getCustomer());
            existing.setService(updatedBooking.getService());
            existing.setMechanic(updatedBooking.getMechanic());
            existing.setAppointmentAt(updatedBooking.getAppointmentAt());
            existing.setQuotedPrice(updatedBooking.getQuotedPrice());
            existing.setStatus(updatedBooking.getStatus());
            existing.setNotes(updatedBooking.getNotes());
            return repo.save(existing);
        }).orElse(null);
    }

    @Override
    public boolean deleteBooking(int id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
