package ModelTest;

import Airport.domain.model.CommercialAircraft;
import Airport.domain.model.Passenger;
import Airport.domain.model.PrivateJet;
import org.junit.jupiter.api.Test; // JUnit 5 annotation

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


public class ModelTest {

    @Test // Test for new passenger creation
    void testPassengerCreation() {
        Passenger passenger = new Passenger("John", "P12345");

        // Validate that name and passport number are set correctly
        assertEquals("John", passenger.getName());
        assertEquals("P12345", passenger.getPassportNumber());
    }
    @Test // Aircraft test
    void testCommercialAircraftCreation() {

        CommercialAircraft aircraft = new CommercialAircraft(
                "Boeing 737-800 (738)",
                160,
                6875.0,
                "Delta Airlines");
        assertEquals("Boeing 737-800 (738)", aircraft.getModel());
        assertEquals(160, aircraft.getCapacity());
        assertEquals(6875.0, aircraft.getFuelCapacity());
        assertEquals("Delta Airlines", aircraft.getAirlineName());
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
        assertEquals("Gulfstream G650", jet.getModel());
        assertEquals(19, jet.getCapacity());
        assertEquals(6548.0, jet.getFuelCapacity());
        assertTrue(jet.hasLuxuryService());
        assertEquals(590, jet.getMaxSpeed());

    }
}

