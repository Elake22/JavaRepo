// Handles displaying all media
import  java.util.List;

public class ListAllMediaCommand {

    public void execute(MediaService mediaService, TerminalUtils terminal) {
        terminal.displayMessage("\n--- Media Collection ---");


        // Full list of media items from the service
        List<Media> allMedia = mediaService.getAllMedia();

        // Uses terminalutils to display media list
        terminal.displayMediaList(allMedia);
    }
}
