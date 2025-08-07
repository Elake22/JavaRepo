package com.example.demo;

import com.example.demo.model.Customer;
import com.example.demo.MockRepo.InMemoryCustomerRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.CustomerServiceImpl;

import com.example.demo.model.Mechanic;
import com.example.demo.MockRepo.InMemoryMechanicRepository;
import com.example.demo.service.MechanicService;
import com.example.demo.service.MechanicServiceImpl;

import com.example.demo.model.Services;
import com.example.demo.MockRepo.InMemoryServicesRepository;
import com.example.demo.service.ServicesService;
import com.example.demo.service.ServicesServiceImpl;

import com.example.demo.model.Booking;
import com.example.demo.MockRepo.InMemoryBookingRepository;
import com.example.demo.service.BookingService;
import com.example.demo.service.BookingServiceImpl;


import java.math.BigDecimal;


public class DemoRunner {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerServiceImpl(new InMemoryCustomerRepository());

        // Add a customer
        Customer c1 = new Customer(0, "Nesha", "Lake", "nesha@example.com", "123-456-7890", "Mustang GT");
        customerService.addCustomer(c1);

        // Get all customers
        System.out.println("All Customers:");
        customerService.getAllCustomers().forEach(System.out::println);

        // Initialize mechanic service with in-memory repo
        MechanicService mechanicService = new MechanicServiceImpl(new InMemoryMechanicRepository());

        // Add a mechanic
        Mechanic m1 = new Mechanic(0, "John Smith", 15);
        Mechanic m2 = new Mechanic(0, "Laura Chen", 8);
        mechanicService.addMechanic(m1);
        mechanicService.addMechanic(m2);

        // Get all mechanics
        System.out.println("\nAll Mechanics:");
        mechanicService.getAllMechanics().forEach(System.out::println);

        // Initialize ServicesService with in-memory repo
        ServicesService servicesService = new ServicesServiceImpl(new InMemoryServicesRepository());

        // Add services
        servicesService.addService(new Services(0, "Oil Change", "Regular oil changes to keep your engine running smoothly.", new BigDecimal("39.99")));
        servicesService.addService(new Services(0, "Brake Inspection", "Comprehensive brake system check for optimal safety.", new BigDecimal("59.99")));
        servicesService.addService(new Services(0, "Tire Rotation", "Even out tire wear and extend their lifespan.", new BigDecimal("29.99")));
        servicesService.addService(new Services(0, "Engine Diagnostics", "Advanced computerized diagnostics to identify engine issues.", new BigDecimal("89.99")));
        servicesService.addService(new Services(0, "Battery Replacement", "Check and replace your vehicle's battery if needed.", new BigDecimal("119.99")));
        servicesService.addService(new Services(0, "Transmission Service", "Keep your transmission running smoothly with our service.", new BigDecimal("149.99")));
        servicesService.addService(new Services(0, "A/C Repair", "Stay cool with our air conditioning repair service.", new BigDecimal("99.99")));

        // Get all services
        System.out.println("\nAll Services:");
        servicesService.getAllServices().forEach(System.out::println);

        // Booking Service setup
        BookingService bookingService = new BookingServiceImpl(new InMemoryBookingRepository());

        // Sample booking using existing mock IDs:
        // Customer ID 1 → "Nesha Lake"
        // Mechanic ID 1 → "John Smith"
        // Service ID 1 → "Oil Change" (price: 39.99)
        Booking b1 = new Booking(0, 1, 1, 1, "Morning", new BigDecimal("39.99"));
        Booking b2 = new Booking(0, 2, 2, 3, "Afternoon", new BigDecimal("29.99"));

        bookingService.addBooking(b1);
        bookingService.addBooking(b2);

        // Output all bookings
        System.out.println("\nAll Bookings:");
        bookingService.getAllBookings().forEach(System.out::println);



    }

}
