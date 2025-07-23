package org.example;

// Step 1: Create a Generic Reward Class
// - Define a class `Reward<T>` that stores a reward name and its value.
// - Include a constructor to initialize the reward.
// - Add methods to retrieve the reward details.

class Reward<T> {
    // Declare private variables for reward name and value.

    // Constructor to initialize the reward.

    // Getter methods to retrieve reward name and value.

    // Override the `toString()` method to display reward details.
}

// Step 2: Create a Customer Class
// - Define a class `Customer` that represents a customer in the system.
// - Each customer has a unique ID, name, reward points, and a purchase history.
// - Include methods to add purchases, calculate points, and redeem rewards.

class Customer {
    // Declare private variables for customer ID, name, reward points, and purchase history.

    // Constructor to initialize the customer.

    // Method to add a purchase and update reward points.

    // Method to redeem a reward if enough points are available.

    // Method to display customer details (name, ID, points, and purchase history).

    // Getter methods for customer ID and reward points.
}

// Step 3: Manage Customers with HashMap
// - Create a class `CustomerManager` that stores all customers in a HashMap.
// - The key will be the customer ID, and the value will be the corresponding Customer object.
// - Include methods to add new customers, find a customer, and display all customers.

class CustomerManager {
    // Declare a HashMap to store customers.

    // Variable to keep track of customer count for unique IDs.

    // Method to generate a unique customer ID.

    // Method to add a new customer.

    // Method to find a customer by ID.

    // Method to display all customers.
}

// Step 4: Implement the Main Program
// - Display a menu allowing users to interact with the rewards system.
// - Provide options to add a customer, make a purchase, view details, and redeem rewards.

public class RewardsProgram {
    public static void main(String[] args) {
        // Initialize Scanner for user input.

        // Create an instance of `CustomerManager` to handle customer data.

        // Create a list of available rewards using the `Reward<T>` generic class.

        // Start a loop to display the menu and process user input.

        // Display menu options:
        // 1. Add New Customer
        // 2. Make a Purchase
        // 3. View Customer Details
        // 4. Redeem Reward
        // 5. Exit

        // Use a switch statement to handle user choices.

        // Case 1: Prompt the user to enter a customer name and add the customer.

        // Case 2: Prompt the user to enter a customer ID and purchase amount.
        //         Find the customer and update their points accordingly.

        // Case 3: Prompt the user to enter a customer ID and display customer details.

        // Case 4: Prompt the user to enter a customer ID and display available rewards.
        //         Allow the user to redeem a reward if they have enough points.

        // Case 5: Exit the program.

        // Default case: Handle invalid inputs.

        // Close the Scanner at the end of the program.
    }
}
