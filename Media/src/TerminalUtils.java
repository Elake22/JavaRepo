import java.util.List;
import java.util.Scanner;

// Handles user input
public class TerminalUtils {
    private Scanner scanner;

    // Constructor to start scanner
    public TerminalUtils() {
        scanner = new Scanner(System.in);
    }
    // Display menu options
    public void displayMenu() {
        System.out.println("\n=== Media List Menu ===");
        System.out.println("1. Add Media");
        System.out.println("2. Remove Media");
        System.out.println("3. Play Media");
        System.out.println("4. List All Media");
        System.out.println("5. Exit");
    }
    public int getMenuChoice() {
        int choice = -1;
        while (choice < 1 || choice > 5) {
            System.out.print("Please choose option (1-5): ");
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number 1-5.");
            }
        }
        return choice;
    }
    public String getString(String prompt) { //Prompts user and returns string
        System.out.print(prompt);
        return  scanner.nextLine().trim();
    }
    

}
