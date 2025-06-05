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
        while (lockerNumber == null) {
            System.out.print("Enter locker number: ");
            String input = scanner.nextLine();
            lockerNumber = parseIntSafe(input);
            if (lockerNumber == null) {
                System.out.println("Invalid input. Please enter a number 1-5.");
            }
        }
        return lockerNumber;
    }
    // Prompts the user to enter their PIN
    public static String getLockerPin(Scanner scanner) {
        System.out.print("Enter PIN: ");
        return scanner.nextLine();
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

