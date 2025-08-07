package com.example.demo;

import com.example.demo.model.Customer;
import com.example.demo.model.Mechanic;
import com.example.demo.model.Services;
import com.example.demo.model.Booking;
import com.example.demo.service.CustomerService;
import com.example.demo.service.MechanicService;
import com.example.demo.service.ServicesService;
import com.example.demo.service.BookingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("mock") // Only runs when 'mock' profile is active
public class DemoRunner implements CommandLineRunner {

    private final CustomerService customerService;
    private final MechanicService mechanicService;
    private final ServicesService servicesService;
    private final BookingService bookingService;

    public DemoRunner(CustomerService customerService,
                      MechanicService mechanicService,
                      ServicesService servicesService,
                      BookingService bookingService) {
        this.customerService = customerService;
        this.mechanicService = mechanicService;
        this.servicesService = servicesService;
        this.bookingService = bookingService;
    }

    @Override
    public void run(String... args) {
        // Add mock customers
        customerService.addCustomer(new Customer(0, "Nesha", "Lake", "nesha@example.com", "123-456-7890", "Mustang GT"));
        customerService.addCustomer(new Customer(0, "Elijah", "John", "elijah@example.com", "555-555-1234", "Mercedes C63"));

        // Add mock mechanics
        mechanicService.addMechanic(new Mechanic(0, "John Smith", 15));
        mechanicService.addMechanic(new Mechanic(0, "Laura Chen", 8));

        // Add services
        servicesService.addService(new Services(0, "Oil Change", "Keep your engine running smoothly", new BigDecimal("39.99")));
        servicesService.addService(new Services(0, "Brake Inspection", "Check your brakes", new BigDecimal("59.99")));
        servicesService.addService(new Services(0, "Tire Rotation", "Even out tire wear", new BigDecimal("29.99")));
        servicesService.addService(new Services(0, "Engine Diagnostics", "Identify engine issues", new BigDecimal("89.99")));
        servicesService.addService(new Services(0, "Battery Replacement", "Check and replace battery", new BigDecimal("119.99")));
        servicesService.addService(new Services(0, "Transmission Service", "Keep your transmission healthy", new BigDecimal("149.99")));
        servicesService.addService(new Services(0, "A/C Repair", "Fix your air conditioning", new BigDecimal("99.99")));

        // Add mock bookings
        bookingService.addBooking(new Booking(0, 1, 1, 1, "Morning", new BigDecimal("39.99")));
        bookingService.addBooking(new Booking(0, 2, 2, 3, "Afternoon", new BigDecimal("29.99")));

        // Output confirmation
        System.out.println("✅ Mock data loaded (customers, mechanics, services, bookings).");
    }
}
