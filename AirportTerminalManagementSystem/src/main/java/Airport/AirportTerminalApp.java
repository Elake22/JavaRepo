package Airport;

import Airport.data.ReservationStorage;
import Airport.data.CSVUtil;
import Airport.view.ConsoleUI;
import Airport.domain.model.*;
import Airport.domain.reservation.ReservationSystem;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import Airport.domain.loyalty.*;

import Airport.view.ConsoleUI;
import Airport.view.UI;

public class AirportTerminalApp {
    public static void main(String[] args) throws IOException {
        UI ui = new ConsoleUI();
        ReservationStorage storage = new CSVUtil();

        // Step 1: Create Aircraft and Flights
        CommercialAircraft commercialJet = new CommercialAircraft("Boeing 737", 180, 50000.0, "Delta Airlines");
        PrivateJet privateJet = new PrivateJet("Gulfstream G650", 10, 6548.0, true, 600);

        Flight flight1 = new Flight("AA1001", LocalDate.of(2025, 7, 1), new BigDecimal("350.99"), commercialJet);
        Flight flight2 = new Flight("PJ007", LocalDate.of(2025, 7, 5), new BigDecimal("5000.00"), privateJet);

        // Map of flights (needed for CSV writing)
        Map<String, Flight> flightMap = new HashMap<>();
        flightMap.put(flight1.getFlightNumber(), flight1);
        flightMap.put(flight2.getFlightNumber(), flight2);

        // Step 2: Create Reservation System
        ReservationSystem reservationSystem = new ReservationSystem();

        // Step 3: Add Passengers to Flights
        reservationSystem.addReservation("AA1001", new Passenger("John Smith", "P12345",new RegularPassenger()));
        reservationSystem.addReservation("AA1001", new Passenger("Bob Jones", "P67890",new VIPPassenger()));
        reservationSystem.addReservation("PJ007", new Passenger("Tony Stark", "IRON01", new RegularPassenger()));

        // Step 4: Save reservations to CSV
        String filePath = "data/reservations.csv";
        storage.save(filePath, reservationSystem.getAllReservations(), flightMap);
        ui.confirmSave(filePath);

        // Step 5: Load reservations from CSV
        ReservationSystem loadedSystem = new ReservationSystem();
        Map<String, Flight> loadedFlights = storage.load(filePath, loadedSystem);


        // Step 6: Display passengers for a flight
        //ui.displayLoadedFlights(loadedSystem.getAllReservations());
        ui.displayHeader("Passenger List");
        ui.displayCsvFormat(loadedSystem.getAllReservations(), loadedFlights);

        // Loyalty Program
        ui.displayDiscountedPassengers("AA1001", loadedSystem.getPassengersForFlight("AA1001"), loadedFlights.get("AA1001").getTicketPrice());

    }
}
