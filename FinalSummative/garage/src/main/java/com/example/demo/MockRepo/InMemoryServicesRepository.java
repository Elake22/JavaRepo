package com.example.demo.MockRepo;

import com.example.demo.model.Services;
import com.example.demo.repository.ServicesRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

// In-memory implementation of the ServicesRepository for testing
@Repository
@Profile("mock")
public class InMemoryServicesRepository implements ServicesRepository {

    private final Map<Integer, Services> servicesMap = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    private void initializeSampleData() {
        save(new Services(0, "Oil Change", "Regular oil changes to keep your engine running smoothly.", new BigDecimal("39.99")));
        save(new Services(0, "Brake Inspection", "Comprehensive brake system check for optimal safety.", new BigDecimal("59.99")));
        save(new Services(0, "Tire Rotation", "Even out tire wear and extend their lifespan.", new BigDecimal("29.99")));
        save(new Services(0, "Engine Diagnostics", "Advanced computerized diagnostics to identify engine issues.", new BigDecimal("89.99")));
        save(new Services(0, "Battery Replacement", "Check and replace your vehicle's battery if needed.", new BigDecimal("119.99")));
        save(new Services(0, "Transmission Service", "Keep your transmission running smoothly with our service.", new BigDecimal("149.99")));
        save(new Services(0, "A/C Repair", "Stay cool with our air conditioning repair service.", new BigDecimal("99.99")));
    }

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