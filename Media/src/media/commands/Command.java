package media.commands;

import media.MediaService;
import media.utils.TerminalUtils;

public interface Command {
    void execute(MediaService mediaService, TerminalUtils terminal);
}