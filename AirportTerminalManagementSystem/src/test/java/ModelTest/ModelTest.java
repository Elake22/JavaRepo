package ModelTest;

import Airport.domain.model.Passenger;
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
}

