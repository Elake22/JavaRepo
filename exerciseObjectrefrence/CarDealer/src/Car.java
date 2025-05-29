// Car.java
public class Car {
    // Properties (Instance Variables)
    String brand;
    String model;
    int year;

    // Constructor to initialize the car
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    // Method to display car details
    public void displayInfo() {
        System.out.println("Car: " + brand + " " + model + " (" + year + ")");
    }
}