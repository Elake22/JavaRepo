package media.commands;// Handles removing a media item from the list by name

public class RemoveMediaCommand {

    public void execute(MediaService mediaService, TerminalUtils terminal) {
        terminal.displayMessage("\n--- Remove media.models.Media ---");

        // Ask for media to remove
        String name = terminal.getString("Enter the name of the media to remove: ");

        // Remove media
        boolean removed = mediaService.removeMediaByName(name);

        // Pass or fail to remove
        if (removed) {
            terminal.displayMessage("✅ media.models.Media \"" + name + "\" removed successfully.");
        } else {
            terminal.displayMessage("❌ media.models.Media \"" + name + "\" not found.");
        }
    }
}