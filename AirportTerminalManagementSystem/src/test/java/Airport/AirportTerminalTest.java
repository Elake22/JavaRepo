package Airport;

import Airport.data.CSVUtil;
import Airport.domain.loyalty.*;
import Airport.domain.model.*;
import Airport.domain.reservation.ReservationSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

// These tests verify that:
// Passengers are created with correct loyalty programs
// Discounts are applied properly through the system
// Data is correctly saved to and loaded from CSV

public class AirportTerminalTest {

    private ReservationSystem reservationSystem;
    private Map<String, Flight> flightMap;
    private String filePath;

    @BeforeEach
    void setup() {
        reservationSystem = new ReservationSystem();
        flightMap = new HashMap<>();
        filePath = "data/test_reservations.csv";

        // Aircraft and flights
        CommercialAircraft commercialJet = new CommercialAircraft("Boeing 737", 180, 50000.0, "Delta Airlines");
        PrivateJet privateJet = new PrivateJet("Gulfstream G650", 10, 6548.0, true, 600);

        Flight flight1 = new Flight("AA1001", LocalDate.of(2025, 7, 1), new BigDecimal("350.00"), commercialJet);
        Flight flight2 = new Flight("PJ007", LocalDate.of(2025, 7, 5), new BigDecimal("4999.00"), privateJet);

        flightMap.put(flight1.getFlightNumber(), flight1);
        flightMap.put(flight2.getFlightNumber(), flight2);

        // Add passengers
        reservationSystem.addReservation("AA1001", new Passenger("John Smith", "P12345", new RegularPassenger()));
        reservationSystem.addReservation("AA1001", new Passenger("Bob Jones", "P67890", new VIPPassenger()));
        reservationSystem.addReservation("PJ007", new Passenger("Tony Stark", "IRON01", new RegularPassenger()));
    }
    @Test
    void testSaveAndLoadWithLoyaltyProgram() throws Exception {
        // Save to CSV
        CSVUtil.saveReservationsToCSV(filePath, reservationSystem.getAllReservations(), flightMap);

        // Load into new system
        ReservationSystem loadedSystem = new ReservationSystem();
        Map<String, Flight> loadedFlights = CSVUtil.loadReservationsFromCSV(filePath, loadedSystem);

        List<Passenger> passengers = loadedSystem.getPassengersForFlight("AA1001");

        assertEquals(2, passengers.size());

        Passenger john = passengers.get(0);
        Passenger bob = passengers.get(1);

        assertTrue(john.getLoyaltyProgram() instanceof RegularPassenger, "John should be Regular");
        assertTrue(bob.getLoyaltyProgram() instanceof VIPPassenger, "Bob should be VIP");

        // Check discount application
        BigDecimal johnFinal = john.getDiscountedPrice(new BigDecimal("350.00"));
        BigDecimal bobFinal = bob.getDiscountedPrice(new BigDecimal("350.00"));

        assertEquals(new BigDecimal("350.00"), johnFinal, "Regular should pay full");
        assertEquals(new BigDecimal("280.00"), bobFinal, "VIP should get 20% off");
    }

    @Test
    void testCSVFileIsCreated() throws Exception {
        CSVUtil.saveReservationsToCSV(filePath, reservationSystem.getAllReservations(), flightMap);
        File file = new File(filePath);
        assertTrue(file.exists(), "CSV file should exist after save");
    }
}

