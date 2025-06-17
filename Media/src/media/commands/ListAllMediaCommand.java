package media.commands;

import media.MediaService;
import media.models.Media;
import media.utils.TerminalUtils;
import java.util.List;

public class ListAllMediaCommand implements Command {

    @Override
    public void execute(MediaService mediaService, TerminalUtils terminal) {
        terminal.displayMessage("\n--- Media Collection ---");


        // Full list of media items from the service
        List<Media> allMedia = mediaService.getAllMedia();

        // Uses terminalutils to display media list
        terminal.displayMediaList(allMedia);
    }
}
