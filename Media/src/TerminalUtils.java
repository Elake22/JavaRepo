import media.models.Media;

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
        System.out.println("\n=== media.models.Media List Menu ===");
        System.out.println("1. Add media.models.Media");
        System.out.println("2. Remove media.models.Media");
        System.out.println("3. Play media.models.Media");
        System.out.println("4. List All media.models.Media");
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
        return scanner.nextLine().trim();
    }
    // Prompts the user and returns their input as an integer, with validation
    public int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
    public void displayMessage(String message) { // Displays message to user
        System.out.println(message);
    }

    // Display a list of media items
    public void displayMediaList(List<Media> mediaList) {
        if (mediaList.isEmpty()) {
            System.out.println("No media items found.");
        } else {
            System.out.println("\nAll media.models.Media in Library:");
            int index = 1;

            for (Media media : mediaList) {
                // Get class name (e.g., Video, Audio, media.models.Book, etc.)
                String mediaType = media.getClass().getSimpleName();
                System.out.println(index + ". " + mediaType + ": " + media.getName());
                System.out.println("   Description: " + media.getDescription());
                index++;
            }
        }
    }
}


