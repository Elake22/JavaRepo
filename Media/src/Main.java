// Main class that control the application loop
public class Main {
    public static void main(String[] args) {

        MediaService mediaService = new MediaService();     // Manages media collection
        TerminalUtils terminal = new TerminalUtils();       // Handles user input/output
        boolean running = true;                             // Controls the application loop

        // Main menu loop
        while (running) {
            terminal.displayMenu();
            int choice = terminal.getMenuChoice(); // Menu choice

            switch (choice) {
                case 1:
                    new AddMediaCommand().executes(mediaService,terminal);
                    break;

                case 2:
                    new RemoveMediaCommand().execute(mediaService, terminal);
                    break;

                case 3:
                    new PlayMediaCommand().execute(mediaService, terminal);
                    break;

                case 4:new ListAllMediaCommand().execute(mediaService, terminal); // placeholder
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
