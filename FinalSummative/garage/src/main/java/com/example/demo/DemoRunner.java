package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
        // Skip if already seeded (prevents dupes on restart)
        if (!servicesService.getAllServices().isEmpty()) {
            System.out.println("ℹ️  Seed skipped: services already present.");
            return;
        }

        // ---- Customers
        Customer nesha = new Customer();
        nesha.setFirstName("Nesha");
        nesha.setLastName("Lake");
        nesha.setPhone("123-456-7890");
        nesha.setEmail("nesha@example.com");
        nesha.setCarModel("Civic");
        nesha = customerService.addCustomer(nesha);

        Customer elijah = new Customer();
        elijah.setFirstName("Elijah");
        elijah.setLastName("John");
        elijah.setPhone("555-555-1234");
        elijah.setEmail("elijah@example.com");
        elijah.setCarModel("Accord");
        elijah = customerService.addCustomer(elijah);

        // ---- Mechanics
        Mechanic john = new Mechanic();
        john.setName("John Smith");
        john.setSpecialty("Engine Specialist");
        john.setYearsExperience(15);
        john = mechanicService.addMechanic(john);

        Mechanic laura = new Mechanic();
        laura.setName("Laura Chen");
        laura.setSpecialty("Brake Systems");
        laura.setYearsExperience(8);
        laura = mechanicService.addMechanic(laura);

        // ---- Services
        Services oilChange = new Services();
        oilChange.setName("Oil Change");
        oilChange.setDescription("Keep your engine running smoothly");
        oilChange.setPrice(new java.math.BigDecimal("39.99"));
        oilChange = servicesService.addService(oilChange);

        Services brakeInspect = new Services();
        brakeInspect.setName("Brake Inspection");
        brakeInspect.setDescription("Check your brakes");
        brakeInspect.setPrice(new java.math.BigDecimal("59.99"));
        brakeInspect = servicesService.addService(brakeInspect);

        Services tireRotation = new Services();
        tireRotation.setName("Tire Rotation");
        tireRotation.setDescription("Even out tire wear");
        tireRotation.setPrice(new java.math.BigDecimal("29.99"));
        tireRotation = servicesService.addService(tireRotation);

        Services engineDiag = new Services();
        engineDiag.setName("Engine Diagnostics");
        engineDiag.setDescription("Identify engine issues");
        engineDiag.setPrice(new java.math.BigDecimal("89.99"));
        engineDiag = servicesService.addService(engineDiag);

        Services battery = new Services();
        battery.setName("Battery Replacement");
        battery.setDescription("Check and replace battery");
        battery.setPrice(new java.math.BigDecimal("119.99"));
        battery = servicesService.addService(battery);

        Services transSvc = new Services();
        transSvc.setName("Transmission Service");
        transSvc.setDescription("Keep your transmission healthy");
        transSvc.setPrice(new java.math.BigDecimal("149.99"));
        transSvc = servicesService.addService(transSvc);

        Services acRepair = new Services();
        acRepair.setName("A/C Repair");
        acRepair.setDescription("Fix your air conditioning");
        acRepair.setPrice(new java.math.BigDecimal("99.99"));
        acRepair = servicesService.addService(acRepair);

        // ---- Bookings
        java.time.LocalDateTime morning =
                java.time.LocalDateTime.now().plusDays(1).withHour(9).withMinute(0).withSecond(0).withNano(0);

        java.time.LocalDateTime afternoon =
                java.time.LocalDateTime.now().plusDays(2).withHour(14).withMinute(0).withSecond(0).withNano(0);

        Booking b1 = new Booking();
        b1.setCustomer(nesha);
        b1.setService(oilChange);
        b1.setMechanic(john);
        b1.setAppointmentAt(morning);
        b1.setQuotedPrice(new java.math.BigDecimal("39.99"));
        b1.setStatus("SCHEDULED");
        b1.setNotes("Morning slot");
        bookingService.addBooking(b1);

        Booking b2 = new Booking();
        b2.setCustomer(elijah);
        b2.setService(tireRotation);
        b2.setMechanic(laura);
        b2.setAppointmentAt(afternoon);
        b2.setQuotedPrice(new java.math.BigDecimal("29.99"));
        b2.setStatus("SCHEDULED");
        b2.setNotes("Afternoon slot");
        bookingService.addBooking(b2);

        System.out.println("✅ Mock data loaded (customers, mechanics, services, bookings).");
    }
}