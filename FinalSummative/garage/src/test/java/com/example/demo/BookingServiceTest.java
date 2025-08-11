package com.example.demo;

import com.example.demo.MockRepo.InMemoryBookingRepository;
import com.example.demo.model.Booking;
import com.example.demo.service.BookingService;
import com.example.demo.service.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTest {
    private BookingService service;

    @BeforeEach
    void setUp() {
        service = new BookingServiceImpl(new InMemoryBookingRepository());
    }
    @Test
    void testAddBookingSuccess() {
        Booking booking = new Booking(0, 1, 1, 1, "Morning", new BigDecimal("39.99"));
        Booking added = service.addBooking(booking);
        assertNotNull(added);
        assertEquals("Morning", added.getTimePreference());
    }
    @Test
    void testGetAllBookings() {
        service.addBooking(new Booking(0, 2, 2, 2, "Afternoon", new BigDecimal("59.99")));
        service.addBooking(new Booking(0, 3, 3, 3, "Morning", new BigDecimal("89.99")));
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
        Booking original = service.addBooking(new Booking(0, 4, 4, 4, "Morning", new BigDecimal("99.99")));
        int id = original.getId();
        Booking updated = new Booking(id, 4, 4, 4, "Afternoon", new BigDecimal("119.99"));

        Booking result = service.updateBooking(id, updated);
        assertNotNull(result);
        assertEquals("Afternoon", result.getTimePreference());
        assertEquals(new BigDecimal("119.99"), result.getEstimatedTotal());
    }
    @Test
    void testUpdateBookingFail_NotFound() {
        Booking updated = new Booking(999, 1, 1, 1, "Afternoon", new BigDecimal("0.00"));
        Booking result = service.updateBooking(999, updated);
        assertNull(result);
    }
    @Test
    void testDeleteBookingSuccess() {
        Booking booking = service.addBooking(new Booking(0, 5, 5, 5, "Morning", new BigDecimal("29.99")));
        boolean deleted = service.deleteBooking(booking.getId());
        assertTrue(deleted);
        assertNull(service.getBookingById(booking.getId()));
    }

    @Test
    void testDeleteBookingFail_NotFound() {
        boolean result = service.deleteBooking(999);
        assertFalse(result);
    }

}
