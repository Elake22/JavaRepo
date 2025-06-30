package Airport.domain.model;

import java.math.BigDecimal;   // Used for money values
import java.time.LocalDate;    // Year-month-day date

// Represents a flight with schedule, price, and aircraft
public class Flight {
    private final String flightNumber;        // Unique identifier, "AA2200"
    private final LocalDate departureDate;    // Date of departure (yyyy-mm-dd)
    private final BigDecimal ticketPrice;     // Price of a ticket in USD
    private final Aircraft aircraft;

    public Flight(String flightNumber, LocalDate departureDate, BigDecimal ticketPrice, Aircraft aircraft) {
        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.ticketPrice = ticketPrice;
        this.aircraft = aircraft;
    }
    // Flight number getter
    public String getFlightNumber() {
        return flightNumber;
    }
    // Departure getter
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    // Ticket price getter
    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }
    // Aircraft getter
    public Aircraft getAircraft() {
        return aircraft;
    }
}
