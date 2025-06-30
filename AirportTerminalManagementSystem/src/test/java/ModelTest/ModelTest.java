package ModelTest;

import Airport.domain.model.CommercialAircraft;
import Airport.domain.model.Flight;
import Airport.domain.model.Passenger;
import Airport.domain.model.PrivateJet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test; // JUnit 5 annotation


import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class ModelTest {

    @Test // Test for new passenger creation
    void testPassengerCreation() {
        Passenger passenger = new Passenger("John", "P12345");

        // Validate that name and passport number are set correctly
        Assertions.assertEquals("John", passenger.getName());
        Assertions.assertEquals("P12345", passenger.getPassportNumber());
    }
    @Test // Aircraft test
    void testCommercialAircraftCreation() {

        CommercialAircraft aircraft = new CommercialAircraft(
                "Boeing 737-800 (738)",
                160,
                6875.0,
                "Delta Airlines");
        Assertions.assertEquals("Boeing 737-800 (738)", aircraft.getModel());
        Assertions.assertEquals(160, aircraft.getCapacity());
        Assertions.assertEquals(6875.0, aircraft.getFuelCapacity());
        Assertions.assertEquals("Delta Airlines", aircraft.getAirlineName());
    }
    @Test
    void testPrivateJetCreation() {
        PrivateJet jet = new PrivateJet(
                "Gulfstream G650",
                19,
                6548.0,
                true,
                590
        );
        Assertions.assertEquals("Gulfstream G650", jet.getModel());
        Assertions.assertEquals(19, jet.getCapacity());
        Assertions.assertEquals(6548.0, jet.getFuelCapacity());
        assertTrue(jet.hasLuxuryService());
        Assertions.assertEquals(590, jet.getMaxSpeed());
    }
    // Flight Test
    @Test
    void testFlightCreationCommercial() {
        CommercialAircraft aircraft = new CommercialAircraft("Boeing 747", 450,50000.0, "Delta");
        LocalDate date = LocalDate.of(2025, 6,30);
        BigDecimal price = new BigDecimal("350.99");

        Flight flight = new Flight("AA1001", date, price, aircraft);

        assertEquals("AA1001", flight.getFlightNumber());
        assertEquals(date, flight.getDepartureDate());
        assertEquals(new BigDecimal("350.99"), flight.getTicketPrice());
        assertEquals(aircraft, flight.getAircraft());
    }
    @Test
    void testFlightCreationPrivateJet() {
        PrivateJet jet = new PrivateJet("Learjet 75", 8, 900.0, true, 500);
        LocalDate date = LocalDate.of(2025, 6,30);
        BigDecimal price = new BigDecimal("4999.99");

        Flight flight = new Flight("PJ007",date, price, jet);

        assertEquals("PJ007", flight.getFlightNumber());
        assertEquals(LocalDate.of(2025, 6, 30), flight.getDepartureDate());
        assertEquals(new BigDecimal("4999.99"), flight.getTicketPrice());
        assertEquals(jet, flight.getAircraft());
    }
}

