package media.commands;// Handles displaying all media
import media.models.Media;

import  java.util.List;

public class ListAllMediaCommand {

    public void execute(MediaService mediaService, TerminalUtils terminal) {
        terminal.displayMessage("\n--- media.models.Media Collection ---");


        // Full list of media items from the service
        List<Media> allMedia = mediaService.getAllMedia();

        // Uses terminalutils to display media list
        terminal.displayMediaList(allMedia);
    }
}
