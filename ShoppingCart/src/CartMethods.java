import java.util.Scanner;

public class CartMethods {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        System.out.println("Welcome to the shopping cart app!");

        // Arrays for addresses and sizes
        String[] shippingAddresses = {"123 Main St", "456 Main St", "789 Main St"};
        String[] productSizes = {"small", "medium", "large"};

        // Display address options
        System.out.println("Shipping Addresses:");
        for (int i = 0; i < shippingAddresses.length; i++) {
            System.out.println((i + 1) + ". " + shippingAddresses[i]);
        }

        // Display and choose address
        int shippingChoice = promptUserForInt("Shipping address? ");
        String chosenAddress = shippingAddresses[shippingChoice - 1];

        // Enter quantity
        System.out.print("Order quantity? ");
        int quantity = scanner.nextInt();

        // Display and choose size
        System.out.println("Available Sizes:");
        displayChoices(productSizes);
        int sizeChoice = promptUserForInt("Size? ");
        String chosenSize = productSizes[sizeChoice - 1];

        // Check for promo code
        scanner.nextLine(); // clear input buffer
        System.out.print("Promo code for free shipping? ");
        String promo = scanner.nextLine();
        boolean freeShipping = promo.equalsIgnoreCase("FREESHIP");

        // Print order summary
        System.out.println("\nOrder Summary:");
        System.out.println("Address: " + chosenAddress);
        System.out.println("Quantity: " + quantity);
        System.out.println("Size: " + chosenSize);
        System.out.println("Free Shipping: " + (freeShipping ? "Yes" : "No"));

        scanner.close();
    }

    // Method: Display a list of choices
    public static void displayChoices(String[] options) {
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }
    // Method: Sting input
    public static String promptUserForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    // Method: Integer input
    public static int promptUserForInt(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }
}



