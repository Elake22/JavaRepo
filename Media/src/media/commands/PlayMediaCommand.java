package media.commands;// Handles playing a selected media item by name
import media.models.Media;

import java.util.List;
public class PlayMediaCommand {

    public void execute(MediaService mediaService, TerminalUtils terminal) {
        terminal.displayMessage("\n--- Play media.models.Media ---");

        List<Media> allMedia = mediaService.getAllMedia();

        // Check if the media list is empty
        if (mediaService.isEmpty()) {
            terminal.displayMessage("No media available to play.");
            return;
        }
        // Display list of media with numbers
        terminal.displayMessage("Select media to play:");
        for (int i = 0; i < allMedia.size(); i++) {
            System.out.println((i + 1) + ". " + allMedia.get(i).getName());
        }

        // Prompt user to choose a number between 1 and list size
        int choice = -1;
        while (choice < 1 || choice > allMedia.size()) {
            choice = terminal.getInt("Choose media (1-" + allMedia.size() + "): ");
        }

        // Play the selected media
        Media selected = allMedia.get(choice - 1);
        selected.play();

    }
}
