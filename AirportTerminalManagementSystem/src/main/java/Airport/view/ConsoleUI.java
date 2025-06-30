package Airport.view;


import Airport.domain.model.CommercialAircraft;
import Airport.domain.model.Flight;
import Airport.domain.model.Passenger;

import java.util.List;
import java.util.Map;

public class ConsoleUI {

    public void displayHeader(String message) {
        System.out.println("\n==== " + message + " ====");
    }

    public void displayPassengerList(String flightNumber, List<Passenger> passengers) {
        System.out.println("Passengers on flight " + flightNumber + ":");
        for (Passenger p : passengers) {
            System.out.println("- " + p.getName() + " (Passport: " + p.getPassportNumber() + ")");
        }
    }
    public void confirmSave(String filePath) {
        System.out.println("\nReservations saved to file: " + filePath);
    }

    public void displayLoadedFlights(Map<String, List<Passenger>> reservations) {
        for (String flightNumber : reservations.keySet()) {
            // Use the existing displayHeader method for clean output
            displayHeader("Loaded Passenger List for " + flightNumber);
            displayPassengerList(flightNumber, reservations.get(flightNumber));
        }
    }
    public void displayCsvFormat(Map<String, List<Passenger>> reservations, Map<String, Flight> flights) {
        // Header row with aligned column titles
        System.out.printf("%-10s %-12s %-8s %-20s %-15s %-20s %-12s%n",
                "Flight", "Date", "Price", "Passenger", "Passport", "Aircraft", "Type");
        // Row data
        for (String flightNumber : reservations.keySet()) {
            Flight flight = flights.get(flightNumber);
            if (flight == null) continue;

            for (Passenger p : reservations.get(flightNumber)) {
                // Determine the type based on the class
                String type = (flight.getAircraft() instanceof CommercialAircraft) ? "Commercial" : "PrivateJet";

                System.out.printf("%-10s %-12s %-8s %-20s %-15s %-20s %-12s%n",
                        flightNumber,
                        flight.getDepartureDate(),
                        flight.getTicketPrice(),
                        p.getName(),
                        p.getPassportNumber(),
                        flight.getAircraft().getModel(),
                        type);
            }
        }
    }
}


