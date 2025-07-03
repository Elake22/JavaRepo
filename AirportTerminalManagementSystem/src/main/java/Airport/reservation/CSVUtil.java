package Airport.reservation;

import Airport.domain.loyalty.LoyaltyProgram;
import Airport.domain.loyalty.VIPPassenger;
import Airport.domain.loyalty.RegularPassenger;
import Airport.domain.model.*;
import Airport.domain.reservation.ReservationSystem;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

// Utility class for saving and loading reservations to/from a CSV file
public class CSVUtil implements ReservationRepository {

    // Saves reservations to CSV file, one row per passenger
    public static void saveReservationsToCSV(
            String filePath,
            Map<String, List<Passenger>> reservations,
            Map<String, Flight> flightMap) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {

            // Loop through each flight in the reservation map
            for (String flightNumber : reservations.keySet()) {
                List<Passenger> passengers = reservations.get(flightNumber);

                // Look up flight details
                Flight flight = flightMap.get(flightNumber);
                if (flight == null) continue; // Skip if flight not found

                writer.write(String.format("%-8s | %-12s | %-7s | %-10s | %-15s | %-10s | %-16s | %-10s",
                        "Flight", "Date", "Price", "FinalPrice", "Passenger", "Passport", "Aircraft", "Type"));

                writer.newLine();


                // Write each passenger on this flight as a line in CSV and determine type
                for (Passenger passenger : passengers) {
                    writer.write(getString(passenger, flight));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing reservations to CSV: " + e.getMessage());
        }
    }

    private static String getString(Passenger passenger, Flight flight) {
        String aircraftType = (flight.getAircraft() instanceof CommercialAircraft)
                ? "Commercial"
                : "PrivateJet";

        BigDecimal discounted = passenger.getDiscountedPrice(flight.getTicketPrice());

        String loyalty = (passenger.getLoyaltyProgram() instanceof VIPPassenger) ? "VIP"
                : (passenger.getLoyaltyProgram() instanceof RegularPassenger) ? "Regular"
                : "None";

        return String.format(
                "%-8s | %-12s | %-7s | %-10s | %-15s | %-10s | %-16s | %-10s | %-10s",
                flight.getFlightNumber(),
                flight.getDepartureDate(),
                flight.getTicketPrice().toPlainString(),
                discounted.toPlainString(),
                passenger.getName(),
                passenger.getPassportNumber(),
                flight.getAircraft().getModel(),
                aircraftType,
                loyalty
        );
    }

    // Loads reservations from CSV into a ReservationSystem and returns the reconstructed flights
    public static Map<String, Flight> loadReservationsFromCSV(
            String filePath,
            ReservationSystem system) {
        Map<String, Flight> flights = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                // Skip bad lines or empty rows
                if (parts.length < 9 || parts[0].trim().equalsIgnoreCase("Flight")) continue;

                // Parse fields
                String flightNumber = parts[0].trim();
                LocalDate departureDate = LocalDate.parse(parts[1].trim());
                BigDecimal ticketPrice = new BigDecimal(parts[2].trim());
                BigDecimal discounted = new BigDecimal(parts[3].trim());
                String passengerName = parts[4].trim();
                String passportNumber = parts[5].trim();
                String aircraftModel = parts[6].trim();
                String aircraftType = parts[7].trim();
                String loyaltyType = parts[8].trim();

                // Create aircraft from type
                Aircraft aircraft = aircraftType.equalsIgnoreCase("Commercial")
                        ? new CommercialAircraft(aircraftModel, 0, 0, "")
                        : new PrivateJet(aircraftModel, 0, 0, false, 0);

                // Only create the flight once per flight number
                flights.putIfAbsent(flightNumber, new Flight(flightNumber, departureDate, ticketPrice, aircraft));

                // Recreate loyalty program
                LoyaltyProgram program = switch (loyaltyType) {
                    case "VIP" -> new VIPPassenger();
                    case "Regular" -> new RegularPassenger();
                    default -> null;
                };
                // Recreate passenger
                Passenger passenger = (program != null)
                        ? new Passenger(passengerName, passportNumber, program)
                        : new Passenger(passengerName, passportNumber);


                // Add passenger to the system
                system.addReservation(flightNumber, passenger);
            }

        } catch (IOException e) {
            System.err.println("Error reading from CSV: " + e.getMessage());
        }

        return flights;
    }
    @Override
    public void save(String filePath,
                     Map<String, List<Passenger>> reservations,
                     Map<String, Flight> flightMap) throws IOException {
        saveReservationsToCSV(filePath, reservations, flightMap);
    }

    @Override
    public Map<String, Flight> load(String filePath,
                                    ReservationSystem system) throws IOException {
        return loadReservationsFromCSV(filePath, system);
    }
}




