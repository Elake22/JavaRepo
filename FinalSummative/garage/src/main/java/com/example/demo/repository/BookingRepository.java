package com.example.demo.repository;

import com.example.demo.model.Booking;
import com.example.demo.model.Mechanic;
import com.example.demo.model.Services;
import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {



    // Common, handy finders (optional)
    List<Booking> findByStatus(String status);

    List<Booking> findByAppointmentAtBetween(LocalDateTime start, LocalDateTime end);

    List<Booking> findByMechanicAndAppointmentAtBetween(Mechanic mechanic, LocalDateTime start, LocalDateTime end);

    List<Booking> findByCustomer(Customer customer);

    List<Booking> findByServiceAndStatus(Services service, String status);
}
