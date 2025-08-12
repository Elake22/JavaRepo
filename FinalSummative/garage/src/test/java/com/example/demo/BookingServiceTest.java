package com.example.demo;

import com.example.demo.MockRepo.InMemoryBookingRepository;
import com.example.demo.model.Booking;
import com.example.demo.model.Customer;
import com.example.demo.model.Mechanic;
import com.example.demo.model.Services;
import com.example.demo.service.BookingService;
import com.example.demo.service.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTest {

    private InMemoryBookingRepository repo;
    private BookingService service;

    // Simple fixtures used by tests
    private Customer c1, c2;
    private Mechanic m1, m2;
    private Services s1, s2;

    @BeforeEach
    void setUp() {
        repo = new InMemoryBookingRepository();
        // ensure a clean slate in case the mock preloads data
        // if your repo doesn't have deleteAll(), add it or expose a clear()
        repo.deleteAll();

        service = new BookingServiceImpl(repo);

        // Build minimal related entities via setters (no all-args ctors)
        c1 = makeCustomer("Nesha", "Lake", "123-456-7890", "nesha@example.com");
        c2 = makeCustomer("Elijah", "Lake", "321-654-0987", "elijah@example.com");

        m1 = makeMechanic("John Smith",  "Engine Specialist",     15);
        m2 = makeMechanic("Sarah Johnson","Electrical Systems",    12);

        s1 = makeService("Oil Change",          "Standard oil change", new BigDecimal("39.99"));
        s2 = makeService("Engine Diagnostics",  "Computerized scan",   new BigDecimal("89.99"));
    }

    // ---------- helpers ----------
    private static Customer makeCustomer(String first, String last, String phone, String email) {
        Customer c = new Customer();
        c.setFirstName(first);
        c.setLastName(last);
        c.setPhone(phone);
        c.setEmail(email);
        return c;
    }

    private static Mechanic makeMechanic(String name, String specialty, int years) {
        Mechanic m = new Mechanic();
        m.setName(name);
        m.setSpecialty(specialty);
        m.setYearsExperience(years);
        return m;
    }

    private static Services makeService(String name, String desc, BigDecimal price) {
        Services s = new Services();
        s.setName(name);
        s.setDescription(desc);
        s.setPrice(price);
        return s;
    }

    private static Booking makeBooking(Customer c, Services s, Mechanic m, String status, String price) {
        Booking b = new Booking();
        b.setCustomer(c);
        b.setService(s);
        b.setMechanic(m);
        b.setAppointmentAt(LocalDateTime.now().plusDays(1));
        b.setQuotedPrice(new BigDecimal(price));
        b.setStatus(status);
        b.setNotes(null);
        return b;
    }
    // -----------------------------

    @Test
    void testAddBookingSuccess() {
        Booking toAdd = makeBooking(c1, s1, m1, "SCHEDULED", "39.99");
        Booking added = service.addBooking(toAdd);

        assertNotNull(added);
        assertNotNull(added.getId());
        assertEquals("SCHEDULED", added.getStatus());
        assertEquals(new BigDecimal("39.99"), added.getQuotedPrice());
        assertTrue(added.getAppointmentAt().isAfter(LocalDateTime.now().minusSeconds(1)));
    }

    @Test
    void testGetAllBookings() {
        service.addBooking(makeBooking(c1, s1, m1, "SCHEDULED", "59.99"));
        service.addBooking(makeBooking(c2, s2, m2, "SCHEDULED", "89.99"));

        List<Booking> all = service.getAllBookings();
        assertEquals(2, all.size());
    }

    @Test
    void testGetBookingByIdNotFound() {
        Booking result = service.getBookingById(404);
        assertNull(result);
    }

    @Test
    void testUpdateBookingSuccess() {
        Booking original = service.addBooking(makeBooking(c1, s1, m1, "SCHEDULED", "99.99"));
        Integer id = original.getId();

        Booking patch = new Booking();
        patch.setCustomer(c1);
        patch.setService(s1);
        patch.setMechanic(m1);
        patch.setAppointmentAt(LocalDateTime.now().plusDays(2));
        patch.setQuotedPrice(new BigDecimal("119.99"));
        patch.setStatus("IN_PROGRESS");
        patch.setNotes("Updated");

        Booking result = service.updateBooking(id, patch);

        assertNotNull(result);
        assertEquals("IN_PROGRESS", result.getStatus());
        assertEquals(new BigDecimal("119.99"), result.getQuotedPrice());
        assertEquals("Updated", result.getNotes());
    }

    @Test
    void testUpdateBookingFail_NotFound() {
        Booking patch = new Booking();
        patch.setStatus("IN_PROGRESS");
        Booking result = service.updateBooking(999, patch);
        assertNull(result);
    }

    @Test
    void testDeleteBookingSuccess() {
        Booking b = service.addBooking(makeBooking(c1, s1, m1, "SCHEDULED", "29.99"));
        boolean deleted = service.deleteBooking(b.getId());
        assertTrue(deleted);
        assertNull(service.getBookingById(b.getId()));
    }

    @Test
    void testDeleteBookingFail_NotFound() {
        boolean result = service.deleteBooking(999);
        assertFalse(result);
    }
}
