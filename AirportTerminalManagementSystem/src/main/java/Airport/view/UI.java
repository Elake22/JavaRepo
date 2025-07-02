//Abstracts the way the app interacts with UI

package Airport.view;

import Airport.domain.model.Flight;
import Airport.domain.model.Passenger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface UI {
    void displayHeader(String message);
    void displayPassengerList(String flightNumber, List<Passenger> passengers);
    void displayDiscountedPassengers(String flightNumber, List<Passenger> passengers, BigDecimal basePrice);
    void confirmSave(String filePath);
    void displayCsvFormat(Map<String, List<Passenger>> reservations, Map<String, Flight> flights);
}
