package Airport.data;

import Airport.domain.model.*;
import Airport.domain.reservation.ReservationSystem;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

// Utility class for saving and loading reservations to/from a CSV file
public class CSVUtil {

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

                writer.write(String.format("%-8s | %-12s | %-7s | %-15s | %-10s | %-16s | %-10s",
                        "Flight", "Date", "Price", "Passenger", "Passport", "Aircraft", "Type"));
                writer.newLine();


                // Write each passenger on this flight as a line in CSV and determine type
                for (Passenger passenger : passengers) {
                    String line = getString(passenger, flight);

                    writer.write(line);
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


        String line = String.format("%-8s | %-12s | %-7s | %-15s | %-10s | %-16s | %-10s",
                flight.getFlightNumber(),                      // AA1001
                flight.getDepartureDate().toString(),          // 2025-06-30
                flight.getTicketPrice().toPlainString(),       // 299.99
                passenger.getName(),                           // Bob Smith
                passenger.getPassportNumber(),                 // P12345
                flight.getAircraft().getModel(),               // Boeing 737
                aircraftType                                   // Commercial
        );
        return line;
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
                if (parts.length < 7 || parts[0].trim().equalsIgnoreCase("Flight")) continue;

                // Parse fields
                String flightNumber = parts[0].trim();
                LocalDate departureDate = LocalDate.parse(parts[1].trim());
                BigDecimal ticketPrice = new BigDecimal(parts[2].trim());
                String passengerName = parts[3].trim();
                String passportNumber = parts[4].trim();
                String aircraftModel = parts[5].trim();
                String aircraftType = parts[6].trim();

                // Create aircraft from type
                Aircraft aircraft;
                if (aircraftType.equalsIgnoreCase("Commercial")) {
                    aircraft = new CommercialAircraft(aircraftModel, 0, 0, ""); // dummy airline
                } else {
                    aircraft = new PrivateJet(aircraftModel, 0, 0, false, 0); // dummy jet
                }
                // Only create the flight once per flight number
                flights.putIfAbsent(flightNumber, new Flight(flightNumber, departureDate, ticketPrice, aircraft));

                // Add passenger to the system
                Passenger passenger = new Passenger(passengerName, passportNumber);
                system.addReservation(flightNumber, passenger);
            }
        } catch (IOException e) {
            System.err.println("Error reading from CSV: " + e.getMessage());
        }

        return flights;
    }
}




