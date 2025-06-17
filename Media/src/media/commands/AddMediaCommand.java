package media.commands;

import media.MediaService;
import media.models.Video;
import media.models.Image;
import media.models.Audio;
import media.models.Book;
import media.utils.TerminalUtils;

public class AddMediaCommand implements Command {

    @Override
    public void execute(MediaService mediaService, TerminalUtils terminal) {
        terminal.displayMessage("\n--- Add New Media ---");

        // Prompt user for the media type
        terminal.displayMessage("Select media type:");
        terminal.displayMessage("1. Video");
        terminal.displayMessage("2. Audio");
        terminal.displayMessage("3. Image");
        terminal.displayMessage("4. Book");

        int type = terminal.getInt("Choose type (1-4): ");


        // Prompt for name (common field for all types)
        String name = terminal.getString("Enter name/title: ");

        // Use switch to gather type-specific input
        switch (type) {
            case 1: //"video":
                int vDuration = terminal.getInt("Enter duration in minutes: ");
                String resolution = terminal.getString("Enter resolution (e.g., 1080p): ");
                mediaService.addMedia(new Video(name, vDuration, resolution));
                terminal.displayMessage("✅ Video added successfully!");
                break;

            case 2: //"audio":
                int aDuration = terminal.getInt("Enter duration in minutes: ");
                String artist = terminal.getString("Enter artist name: ");
                mediaService.addMedia(new Audio(name, aDuration, artist));
                terminal.displayMessage("✅ Audio added successfully!");
                break;

            case 3: //"image":
                String dimensions = terminal.getString("Enter dimensions (e.g., 1920x1080): ");
                String format = terminal.getString("Enter file format (e.g., JPEG): ");
                mediaService.addMedia(new Image(name, dimensions, format));
                terminal.displayMessage("✅ Image added successfully!");
                break;

            case 4: //"book":
                String author = terminal.getString("Enter author name: ");
                int pages = terminal.getInt("Enter number of pages: ");
                mediaService.addMedia(new Book(name, author, pages));
                terminal.displayMessage("✅ Book added successfully!");
                break;

            default:
                terminal.displayMessage("❌ Invalid media type. Please enter Video, Audio, Image, or Book.");
        }
    }
}
// type	-Determines which media subclass to create
//switch -	Executes a block for each type
//terminal.getX()	 -Gathers input from the user
//mediaService.addMedia()	- Adds the new item to your list
