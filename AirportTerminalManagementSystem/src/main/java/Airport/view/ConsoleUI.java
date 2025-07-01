package Airport.view;


import Airport.domain.model.CommercialAircraft;
import Airport.domain.model.Flight;
import Airport.domain.model.Passenger;

import java.math.BigDecimal;
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

    public void displayDiscountedPassengers(String flightNumber, List<Passenger> passengers, BigDecimal basePrice) {
        System.out.printf("====Flight Loyalty Members====: %s%n", flightNumber);
        System.out.printf("%-20s %-12s %-12s%n", "Name", "Passport", "Discounted Price");

        for (Passenger p : passengers) {
            BigDecimal finalPrice = p.getDiscountedPrice(basePrice);
            System.out.printf("%-20s %-12s $%-11s%n", p.getName(), p.getPassportNumber(), finalPrice);
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
        // Header
        System.out.printf(
                "%-8s | %-12s | %-10s | %-12s | %-15s | %-10s | %-18s | %-10s%n",
                "Flight", "Date", "Price", "FinalPrice", "Passenger", "Passport", "Aircraft", "Type"
        );
        System.out.println("---------------------------------------------------------------------------------------------------------------------");

        // Row data
        for (String flightNumber : reservations.keySet()) {
            Flight flight = flights.get(flightNumber);
            if (flight == null) continue;

            for (Passenger p : reservations.get(flightNumber)) {
                String type = (flight.getAircraft() instanceof CommercialAircraft) ? "Commercial" : "PrivateJet";
                BigDecimal basePrice = flight.getTicketPrice();
                BigDecimal discounted = p.getDiscountedPrice(basePrice).setScale(2, BigDecimal.ROUND_HALF_UP);

                System.out.printf(
                        "%-8s | %-12s | %-10.2f | %-12.2f | %-15s | %-10s | %-18s | %-10s%n",
                        flightNumber,
                        flight.getDepartureDate(),
                        basePrice,
                        discounted,
                        p.getName(),
                        p.getPassportNumber(),
                        flight.getAircraft().getModel(),
                        type
                );
            }
        }
    }
}


