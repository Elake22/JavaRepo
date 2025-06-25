package learn.memories.data;

import learn.memories.models.Memory;
import java.util.*;

public class MockMemoryRepository implements MemoryRepository {
    private final Map<Integer, Memory> storage = new HashMap<>();
    private int nextId = 1;

    public MockMemoryRepository() {
        Memory m = new Memory();
        m.setId(nextId++);
        m.setFrom("Uncle");
        m.setContent("You were great.");
        m.setShareable(true);
        storage.put(m.getId(), m);
    }

    @Override
    public Memory add(Memory memory) {
        memory.setId(nextId++);
        storage.put(memory.getId(), memory);
        return memory;
    }

    @Override
    public boolean update(Memory memory) {
        if (!storage.containsKey(memory.getId())) return false;
        storage.put(memory.getId(), memory);
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        return storage.remove(id) != null;
    }

    @Override
    public Memory findById(int id) {
        return storage.get(id);
    }

    @Override
    public List<Memory> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public List<Memory> findShareable(boolean shareable) {
        List<Memory> result = new ArrayList<>();
        for (Memory m : storage.values()) {
            if (m.isShareable() == shareable) result.add(m);
        }
        return result;
    }
}
