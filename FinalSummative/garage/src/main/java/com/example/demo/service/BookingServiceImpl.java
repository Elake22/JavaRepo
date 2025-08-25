// src/main/java/com/example/demo/service/BookingServiceImpl.java
package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository repo;

    private static final Set<String> ALLOWED_STATUSES = Set.of(
            "REQUESTED", "SCHEDULED", "CONFIRMED", "IN_PROGRESS", "COMPLETED", "CANCELED"
    );

    public BookingServiceImpl(BookingRepository repo) {
        this.repo = repo;
    }

    // ---------- helpers

    private String normalizeStatus(String s) {
        if (s == null) return "";
        return s.trim().toUpperCase().replace(' ', '_').replace('-', '_');
    }

    private void validateStatusOrThrow(String status) {
        String norm = normalizeStatus(status);
        if (!ALLOWED_STATUSES.contains(norm)) {
            throw new IllegalArgumentException("Invalid status '" + status + "'. Allowed: " + ALLOWED_STATUSES);
        }
    }

    /** Touch lazy fields while the session is open so JSON serialization won’t fail. */
    private void hydrate(Booking b) {
        if (b.getCustomer() != null) {
            b.getCustomer().getFirstName();
            b.getCustomer().getLastName();
            b.getCustomer().getEmail();
            b.getCustomer().getPhone();
            b.getCustomer().getCarModel();
        }
        if (b.getService() != null) {
            b.getService().getName();
            b.getService().getPrice();
            b.getService().getDescription();
        }
        if (b.getMechanic() != null) {
            b.getMechanic().getName();
            b.getMechanic().getSpecialty();
            b.getMechanic().getYearsExperience();
        }
    }

    // ---------- CRUD

    @Override
    @Transactional
    public Booking addBooking(Booking booking) {
        if (booking == null) throw new IllegalArgumentException("Booking is required.");
        if (booking.getAppointmentAt() == null) throw new IllegalArgumentException("appointmentAt is required.");

        LocalDate apptDate = booking.getAppointmentAt().toLocalDate();
        if (apptDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past.");
        }
        if (booking.getCustomer() == null || booking.getCustomer().getId() == null)
            throw new IllegalArgumentException("customer.id is required.");
        if (booking.getService() == null || booking.getService().getId() == null)
            throw new IllegalArgumentException("service.id is required.");
        if (booking.getMechanic() == null || booking.getMechanic().getId() == null)
            throw new IllegalArgumentException("mechanic.id is required.");

        String status = normalizeStatus(booking.getStatus());
        if (status.isBlank()) status = "REQUESTED";
        validateStatusOrThrow(status);
        booking.setStatus(status);

        Booking saved = repo.save(booking);
        hydrate(saved); // <-- IMPORTANT
        return saved;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Booking> getAllBookings() {
        List<Booking> list = repo.findAll();
        list.forEach(this::hydrate);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public Booking getBookingById(int id) {
        return repo.findById(id).map(b -> { hydrate(b); return b; }).orElse(null);
    }

    @Override
    @Transactional
    public Booking updateBooking(int id, Booking updatedBooking) {
        return repo.findById(id).map(existing -> {
            existing.setCustomer(updatedBooking.getCustomer());
            existing.setService(updatedBooking.getService());
            existing.setMechanic(updatedBooking.getMechanic());
            existing.setAppointmentAt(updatedBooking.getAppointmentAt());
            existing.setQuotedPrice(updatedBooking.getQuotedPrice());

            if (updatedBooking.getStatus() != null) {
                String s = normalizeStatus(updatedBooking.getStatus());
                validateStatusOrThrow(s);
                existing.setStatus(s);
            }

            existing.setNotes(updatedBooking.getNotes());

            Booking saved = repo.save(existing);
            hydrate(saved); // <-- IMPORTANT
            return saved;
        }).orElse(null);
    }

    @Override
    @Transactional
    public boolean deleteBooking(int id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public Booking updateStatus(int id, String newStatus) {
        String norm = normalizeStatus(newStatus);
        validateStatusOrThrow(norm);

        return repo.findById(id).map(b -> {
            b.setStatus(norm);
            Booking saved = repo.save(b);
            hydrate(saved); // <-- IMPORTANT
            return saved;
        }).orElse(null);
    }
}
