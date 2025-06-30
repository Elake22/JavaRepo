package ReservationTest;

import Airport.domain.model.Passenger;
import Airport.domain.reservation.ReservationSystem;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationSystemTest {

    void testAddSingleReservation() {
        ReservationSystem system = new ReservationSystem();
        Passenger p1 = new Passenger("Bob", "P12345");

        system.addReservation("AA101", p1);
        List<Passenger> passengers = system.getPassengersForFlight("AA101");

        assertEquals(1, passengers.size());
        assertEquals("Bob", passengers.get(0).getName());
    }

    @Test
    void testAddMultiplePassengersToSameFlight() {
        ReservationSystem system = new ReservationSystem();
        system.addReservation("BB202", new Passenger("Bob", "P10000"));
        system.addReservation("BB202", new Passenger("Bill", "P10001"));

        List<Passenger> passengers = system.getPassengersForFlight("BB202");
        assertEquals(2, passengers.size());
    }

    @Test
    void testGetPassengersForUnknownFlightReturnsEmptyList() {
        ReservationSystem system = new ReservationSystem();
        List<Passenger> passengers = system.getPassengersForFlight("AA9900");

        assertNotNull(passengers); // Should not be null
        assertTrue(passengers.isEmpty()); // Should be empty
    }

    // Passenger removal
    @Test
    void testRemoveExistingPassenger() {
        ReservationSystem system = new ReservationSystem();
        Passenger p = new Passenger("Dave", "P24682");

        system.addReservation("DL4040", p);
        boolean removed = system.removeReservation("DL4040", "P24682");

        assertTrue(removed);
        assertTrue(system.getPassengersForFlight("DL4040").isEmpty());
    }

    @Test
    void testRemovePassengerFromNonexistentFlight() {
        ReservationSystem system = new ReservationSystem();
        boolean result = system.removeReservation("Test1234", "Test001");

        assertFalse(result); // Nothing to remove
    }

    @Test
    void testRemoveNonexistentPassengerFromExistingFlight() {
        ReservationSystem system = new ReservationSystem();
        system.addReservation("AA7777", new Passenger("Eve", "P00001"));

        boolean removed = system.removeReservation("AA7777", "P20001"); // Wrong passport
        assertFalse(removed);

        // Ensure original passenger is still there
        List<Passenger> passengers = system.getPassengersForFlight("AA7777");
        assertEquals(1, passengers.size());
        assertEquals("P00001", passengers.get(0).getPassportNumber());

    }
}