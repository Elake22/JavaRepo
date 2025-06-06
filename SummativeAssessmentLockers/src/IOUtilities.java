// Handles user responses
import java.util.Scanner;
public class IOUtilities {

    // If the input cannot be parsed, return null instead of crashing.
    public static Integer parseIntSafe(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    // Prompts the user to enter locker number
    public static Integer getLockerNumber(Scanner scanner) {
        Integer lockerNumber = null;
        while (lockerNumber == null || lockerNumber < 1 || lockerNumber > 10) {
            System.out.print("Enter locker number (1-10): ");
            String input = scanner.nextLine();
            lockerNumber = parseIntSafe(input);
            if (lockerNumber == null || lockerNumber < 1 || lockerNumber > 10) {
                System.out.println("Invalid input. Please enter a locker number between 1 and 10.");
                lockerNumber = null;
            }
        }
        return lockerNumber;
    }
    // Prompts the user to enter their PIN
    public static String getLockerPin(Scanner scanner) {
        String pin;
        while (true) {
            System.out.print("Enter 4-digit PIN: ");
            pin = scanner.nextLine().trim();
            if (pin.matches("\\d{4}")) {
                return pin;
            } else {
                System.out.println("Invalid PIN. Please enter 4 digits (ex. 1234).");
            }
        }
    }
    // Locker release confirmation
    public static String getConfirmation(Scanner scanner) {
        String input;
        while (true) {
            System.out.print("Are you sure you want to release the locker? (yes/no): ");
            input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y") || input.equals("no") || input.equals("n")) {
                break;
            } else {
                System.out.println("Invalid input.");
            }
        }
        return input;
    }
}

