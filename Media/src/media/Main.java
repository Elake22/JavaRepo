package media;
import media.*;
import media.commands.*;

public class Main {
    public static void main(String[] args) {
        MediaService mediaService = new MediaService();     // Handles media collection
        TerminalUtils terminal = new TerminalUtils();       // Manages input/output
        boolean running = true;

        while (running) {
            terminal.displayMenu();                         // Show menu
            int choice = terminal.getMenuChoice();          // Get user input

            switch (choice) {
                case 1:
                    new AddMediaCommand().execute(mediaService, terminal);
                    break;
                case 2:
                    new RemoveMediaCommand().execute(mediaService, terminal);
                    break;
                case 3:
                    new PlayMediaCommand().execute(mediaService, terminal);
                    break;
                case 4:
                    new ListAllMediaCommand().execute(mediaService, terminal);
                    break;
                case 5:
                    terminal.displayMessage("Exiting Media List. Goodbye!");
                    running = false;
                    break;
                default:
                    terminal.displayMessage("Invalid choice. Please try again.");
            }
        }
    }
}