import java.util.Scanner;
public class ArrayExercise {
    public static void main(String[] args) {

        // 1. Declare & Assign an Array
        // Creating an array of Strings to hold the cities.
        String[] cities = {"Austin", "Dallas", "Houston", "San Antonio", "El Paso"};

        // Print each city by its index in the array
        System.out.println(cities[0]); // Austin
        System.out.println(cities[1]); // Dallas
        System.out.println(cities[2]); // Houston
        System.out.println(cities[3]); // San Antonio
        System.out.println(cities[4]); // El Paso

        // 2. Access & Modify Elements
        cities[2] = "Fort Worth";  //"Houston" to "Fort Worth"

        // 3. Find the Length of an Array
        System.out.println("Total number of cities: " + cities.length);

        //4. Print Array Elements Using a Loop
        // Loop to iterate through all elements in the array
        System.out.println("\nCities using for loop:");
        for (int i = 0; i < cities.length; i++) {
            System.out.println("City " + (i + 1) + ": " + cities[i]);
        }
        // 5: Reverse the Array
        System.out.println("\nCities in reverse order:");
        for (int i = cities.length - 1; i >= 0; i--) {
            System.out.println("City: " + cities[i]);


            // Find a Specific Element check if the city exists in the array

            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter a city name to search for: ");
            String userCity = scanner.nextLine();

            boolean found = false;
            for (String city : cities) {
                if (city.equalsIgnoreCase(userCity)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("City found!");
            } else {
                System.out.println("City not found!");
            }

        }
    }
}