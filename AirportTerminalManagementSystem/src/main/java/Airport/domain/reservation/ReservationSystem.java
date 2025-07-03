package Airport.domain.reservation;

import Airport.domain.model.Passenger;

import java.util.*;

// Will use Maps of flight numbers of passenger lists for reservations
public class ReservationSystem {

    // Key: flight numbers, Value: list of passengers for flight
    private final Map<String, List<Passenger>> reservations = new HashMap<>();

    // Add passenger to flight number
    public boolean addReservation(String flightNumber, Passenger passenger) {
        // If flight exist in Map, add passeneger to list, otherwise makes a new list to add to map
        List<Passenger> passengerList = reservations.computeIfAbsent(flightNumber, k -> new ArrayList<>());

        // Prevent duplicate passengers by passport number
        boolean alreadyExists = passengerList.stream()
                .anyMatch(p -> p.getPassportNumber().equalsIgnoreCase(passenger.getPassportNumber()));

        if (alreadyExists) {
            return false; // Duplicate detected
        }

        passengerList.add(passenger);
        return true;

    } // Retrieves the list of passengers for a given flight number
    public List<Passenger> getPassengersForFlight(String flightNumber) {
        return reservations.getOrDefault(flightNumber, new ArrayList<>());

    }
    // Removes a passenger from a specific flight
    public boolean removeReservation(String flightNumber, String passportNumber) {
        List<Passenger> passengerList = reservations.get(flightNumber);
        if (passengerList == null) return false;

        // Remove passenger by matching passport number
        return passengerList.removeIf(p -> p.getPassportNumber().equals(passportNumber));
    }
    // Shows all reservations
    public  Map<String, List<Passenger>> getAllReservations() {
        return Collections.unmodifiableMap(reservations);
    }

}
