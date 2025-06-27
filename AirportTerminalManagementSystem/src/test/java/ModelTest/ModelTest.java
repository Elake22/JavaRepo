package ModelTest;

import Airport.domain.model.CommercialAircraft;
import Airport.domain.model.Passenger;
import Airport.domain.model.PrivateJet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test; // JUnit 5 annotation


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
}

