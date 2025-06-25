package learn.memories.domain;

import learn.memories.data.MemoryRepository;
import learn.memories.models.Memory;

import java.util.List;

public class MemoryService {
    private final MemoryRepository repository;

    public MemoryService(MemoryRepository repository) {
        this.repository = repository;
    }

    public List<Memory> findPublicMemories() {
        return repository.findShareable(true);
    }

    public List<Memory> findPrivateMemories() {
        return repository.findShareable(false);
    }

    public MemoryResult add(Memory memory) {
        MemoryResult result = validate(memory);
        if (!result.isSuccess()) return result;

        Memory saved = repository.add(memory);
        result.setMemory(saved);
        return result;
    }

    private MemoryResult validate(Memory memory) {
        MemoryResult result = new MemoryResult();

        if (memory == null) {
            result.addErrorMessage("Memory cannot be null.");
            return result;
        }
        if (memory.getFrom() == null || memory.getFrom().isBlank()) {
            result.addErrorMessage("Memory `from` is required.");
        }
        if (memory.getContent() == null || memory.getContent().isBlank()) {
            result.addErrorMessage("Memory `content` is required.");
        }
        if (memory.getContent() != null && memory.getContent().length() < 10) {
            result.addErrorMessage("Memory `content` must be at least 10 characters.");
        }

        return result;
    }
}
