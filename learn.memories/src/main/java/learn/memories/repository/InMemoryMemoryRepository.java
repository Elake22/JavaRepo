// src/main/java/repository/InMemoryMemoryRepository.java
package repository;

import domain.Memory;

import java.util.*;

public class InMemoryMemoryRepository implements MemoryRepository {
    private final Map<String, Memory> storage = new HashMap<>();

    @Override
    public void add(Memory memory) {
        storage.put(memory.getId(), memory);
    }

    @Override
    public boolean update(Memory memory) {
        if (storage.containsKey(memory.getId())) {
            storage.put(memory.getId(), memory);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return storage.remove(id) != null;
    }

    @Override
    public Memory findById(String id) {
        return storage.get(id);
    }

    @Override
    public List<Memory> findAll() {
        return new ArrayList<>(storage.values());
    }
}
