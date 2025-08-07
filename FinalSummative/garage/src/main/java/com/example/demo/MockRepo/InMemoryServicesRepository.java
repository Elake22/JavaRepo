package com.example.demo.MockRepo;

import com.example.demo.model.Services;
import com.example.demo.repository.ServicesRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// In-memory implementation of the ServicesRepository for testing
@Repository
@Profile("mock")
public class InMemoryServicesRepository implements ServicesRepository {

    private final Map<Integer, Services> servicesMap = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    @Override
    public Services save(Services services) {
        services.setId(idGenerator.getAndIncrement());
        servicesMap.put(services.getId(), services);
        return services;
    }

    @Override
    public List<Services> findAll() {
        return new ArrayList<>(servicesMap.values());
    }

    @Override
    public Services findById(int id) {
        return servicesMap.get(id);
    }

    @Override
    public Services update(int id, Services updatedServices) {
        if (servicesMap.containsKey(id)) {
            updatedServices.setId(id);
            servicesMap.put(id, updatedServices);
            return updatedServices;
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return servicesMap.remove(id) != null;
    }
}