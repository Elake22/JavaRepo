package learn.memories.domain;

import learn.memories.models.Memory;
import java.util.ArrayList;
import java.util.List;

public class MemoryResult {
    private boolean success = true;
    private final List<String> errorMessages = new ArrayList<>();
    private Memory memory;

    public boolean isSuccess() {
        return success;
    }

    public void addErrorMessage(String message) {
        errorMessages.add(message);
        success = false;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }
}
