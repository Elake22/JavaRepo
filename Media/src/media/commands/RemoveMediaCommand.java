package media.commands;

import media.MediaService;
import media.utils.TerminalUtils;

public class RemoveMediaCommand implements Command {

    @Override
    public void execute(MediaService mediaService, TerminalUtils terminal) {
        terminal.displayMessage("\n--- Remove Media ---");

        // Ask for media to remove
        String name = terminal.getString("Enter the name of the media to remove: ");

        // Remove media
        boolean removed = mediaService.removeMediaByName(name);

        // Pass or fail to remove
        if (removed) {
            terminal.displayMessage("✅ Media \"" + name + "\" removed successfully.");
        } else {
            terminal.displayMessage("❌ Media \"" + name + "\" not found.");
        }
    }
}
