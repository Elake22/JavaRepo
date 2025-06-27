package Airport.domain.model;

public class Aircraft {

    private final String model; // Ex, Boeing 737
    private final int capacity; // Max passenger count
    private final double fuelCapacity; // Fuel capacity

    public Aircraft(String model, int capacity, double fuelCapacity) {
        this.model = model;
        this.capacity = capacity;
        this.fuelCapacity = fuelCapacity;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }
}
