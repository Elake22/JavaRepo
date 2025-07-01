package Airport;

import Airport.data.CSVUtil;
import Airport.domain.loyalty.*;
import Airport.domain.model.*;
import Airport.domain.reservation.ReservationSystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

// These tests verify that:
// Passengers are created with correct loyalty programs
// Discounts are applied properly through the system
// Data is correctly saved to and loaded from CSV

public class AirportTerminalTest {

    private ReservationSystem reservationSystem;
    private Map<String, Flight> flightMap;
    private String filePath;

    @BeforeEach
    void setup() {
        reservationSystem = new ReservationSystem();
        
    }



}