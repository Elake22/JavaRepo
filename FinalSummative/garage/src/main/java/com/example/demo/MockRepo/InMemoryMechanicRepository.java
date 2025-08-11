package com.example.demo.MockRepo;

import com.example.demo.model.Mechanic;
import com.example.demo.repository.MechanicRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// In-memory implementation of MechanicRepository for testing
@Repository
@Profile("mock")
public class InMemoryMechanicRepository implements MechanicRepository {

    private final Map<Integer, Mechanic> mechanicMap = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    private void initializeSampleData() {
        save(new Mechanic(0, "John Smith", 15));
        save(new Mechanic(0, "Sarah Johnson", 12));
        save(new Mechanic(0, "Mike Rodriguez", 10));
        save(new Mechanic(0, "Laura Chen", 8));
        save(new Mechanic(0, "David Wilson", 14));
    }

    @Override
    public Mechanic save(Mechanic mechanic) {
        mechanic.setId(idGenerator.getAndIncrement());
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
    public Mechanic update(int id, Mechanic updatedMechanic) {
        if (mechanicMap.containsKey(id)) {
            updatedMechanic.setId(id);
            mechanicMap.put(id, updatedMechanic);
            return updatedMechanic;
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return mechanicMap.remove(id) != null;
    }
}