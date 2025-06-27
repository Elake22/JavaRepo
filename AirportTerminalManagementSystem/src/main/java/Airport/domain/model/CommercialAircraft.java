package Airport.domain.model;

public class CommercialAircraft extends Aircraft {

    // Attributes
    private final String airlineName; // Ex. Delta, Southwest, United

    public CommercialAircraft(String model, int capacity, double fuelCapacity, String airlineName) {
        super(model, capacity, fuelCapacity); // Constructor from parent Aircraft
        this.airlineName = airlineName;
    }
    // Airline name getter
    public String getAirlineName() {
        return airlineName;
    }
}
