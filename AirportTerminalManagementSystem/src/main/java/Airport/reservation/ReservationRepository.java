// Abstracts how reservation data is saved and loaded

package Airport.reservation;

import Airport.domain.model.Flight;
import Airport.domain.reservation.ReservationSystem;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ReservationRepository {

    void save(String filePath,
              Map<String, List<Airport.domain.model.Passenger>> reservations,
              Map<String, Flight> flightMap) throws IOException;

    Map<String, Flight> load(String filePath,
                             ReservationSystem system) throws IOException;
}
