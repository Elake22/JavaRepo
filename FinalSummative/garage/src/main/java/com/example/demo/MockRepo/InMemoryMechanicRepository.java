package com.example.demo.MockRepo;

import com.example.demo.model.Mechanic;
import com.example.demo.repository.MechanicRepository;

import java.util.*;

// In-memory implementation of MechanicRepository for testing
public class InMemoryMechanicRepository implements MechanicRepository {

    private final Map<Integer, Mechanic> mechanicMap = new HashMap<>();
    private int currentId = 1;

    @Override
    public Mechanic save(Mechanic mechanic) {
        mechanic.setId(currentId++);
        mechanicMap.put(mechanic.getId(), mechanic);
        return mechanic;
    }

    @Override
    public List<Mechanic> findAll() {
        return new ArrayList<>(mechanicMap.values());
    }

    @Override
    public Mechanic findById(int id) {
        return mechanicMap.get(id);
    }

    @Override
    public Mechanic update(int id, Mechanic mechanic) {
        if (mechanicMap.containsKey(id)) {
            mechanic.setId(id);
            mechanicMap.put(id, mechanic);
            return mechanic;
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return mechanicMap.remove(id) != null;
    }
}
