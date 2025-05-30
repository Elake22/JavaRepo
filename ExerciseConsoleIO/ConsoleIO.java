import java.util.Scanner;

// Receipt IO
public class ConsoleIO {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the order form!");

        // Ask for Name
        System.out.print("What is your name?\n>> ");
        String customerName = scanner.nextLine();

        System.out.println("Hello, " + customerName + "! Let's get started with your order.");
        String productName = scanner.nextLine();

        // Get product details
        System.out.print("What product would you like to purchase?\n>> ");

        System.out.print("How many would you like?\n>> ");
        int quantity = scanner.nextInt();

        System.out.print("What is the unit price?\n>> ");
        double unitPrice = scanner.nextDouble();

        // Calculate totals
        double subtotal = quantity * unitPrice;
        double tax = subtotal * 0.07;
        double grandTotal = subtotal + tax;

        // Print receipt
        System.out.println("\nOrder Summary");
        System.out.println("-------------------------------");
        System.out.println("Item: " + productName);
        System.out.println("Quantity: " + quantity);
        System.out.printf("Unit Price: $%.2f\n", unitPrice);
        System.out.println("-------------------------------");
        System.out.printf("Subtotal: $%.2f\n", subtotal);
        System.out.printf("Tax (7%%): $%.2f\n", tax);
        System.out.printf("Grand Total: $%.2f\n", grandTotal);
        System.out.println("-------------------------------");
        System.out.println("Thank you for your order, " + customerName + "!");

        scanner.close();
    }
}

