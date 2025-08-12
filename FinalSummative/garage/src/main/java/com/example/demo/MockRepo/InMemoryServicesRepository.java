package com.example.demo.MockRepo;

import com.example.demo.model.Services;
import com.example.demo.repository.ServicesRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Repository
@Profile("mock")
public class InMemoryServicesRepository implements ServicesRepository {

    private final Map<Integer, Services> servicesMap = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public InMemoryServicesRepository() {
        initializeSampleData();
    }

    private void initializeSampleData() {
        save(new Services(null, "Oil Change", "Regular oil changes to keep your engine running smoothly.", new BigDecimal("39.99")));
        save(new Services(null, "Brake Inspection", "Comprehensive brake system check for optimal safety.", new BigDecimal("59.99")));
        save(new Services(null, "Tire Rotation", "Even out tire wear and extend their lifespan.", new BigDecimal("29.99")));
        save(new Services(null, "Engine Diagnostics", "Advanced computerized diagnostics to identify engine issues.", new BigDecimal("89.99")));
        save(new Services(null, "Battery Replacement", "Check and replace your vehicle's battery if needed.", new BigDecimal("119.99")));
        save(new Services(null, "Transmission Service", "Keep your transmission running smoothly with our service.", new BigDecimal("149.99")));
        save(new Services(null, "A/C Repair", "Stay cool with our air conditioning repair service.", new BigDecimal("99.99")));
    }

    // ---------- JpaRepository core methods ----------

    @Override
    public <S extends Services> S save(S services) {
        if (services.getId() == null) {
            services.setId(idGenerator.getAndIncrement());
        }
        servicesMap.put(services.getId(), services);
        return services;
    }

    @Override
    public Optional<Services> findById(Integer id) {
        return Optional.ofNullable(servicesMap.get(id));
    }

    @Override
    public List<Services> findAll() {
        return new ArrayList<>(servicesMap.values());
    }

    @Override
    public boolean existsById(Integer id) {
        return servicesMap.containsKey(id);
    }

    @Override
    public long count() {
        return servicesMap.size();
    }

    @Override
    public void deleteById(Integer id) {
        servicesMap.remove(id);
    }

    @Override
    public void delete(Services entity) {
        if (entity.getId() != null) {
            servicesMap.remove(entity.getId());
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Services> entities) {

    }

    @Override
    public void deleteAll() {
        servicesMap.clear();
    }

    // ---------- Bulk ops ----------

    @Override
    public List<Services> findAllById(Iterable<Integer> ids) {
        List<Services> list = new ArrayList<>();
        for (Integer id : ids) {
            Services s = servicesMap.get(id);
            if (s != null) list.add(s);
        }
        return list;
    }

    @Override
    public <S extends Services> List<S> saveAll(Iterable<S> entities) {
        List<S> saved = new ArrayList<>();
        for (S e : entities) saved.add(save(e));
        return saved;
    }

    @Override public void flush() {}
    @Override public <S extends Services> S saveAndFlush(S entity) { return save(entity); }

    @Override
    public <S extends Services> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override public void deleteAllInBatch() { servicesMap.clear(); }
    @Override public void deleteAllInBatch(Iterable<Services> entities) { deleteAll(entities); }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override public Services getOne(Integer id) { return servicesMap.get(id); }
    @Override public Services getById(Integer id) { return servicesMap.get(id); }

    @Override
    public Services getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Services> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Services> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Services> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Services> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Services> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Services> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Services, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    // ---------- Custom finder methods from ServicesRepository ----------

    @Override
    public List<Services> findByNameContainingIgnoreCase(String namePart) {
        String needle = namePart == null ? "" : namePart.toLowerCase();
        List<Services> results = new ArrayList<>();
        for (Services s : servicesMap.values()) {
            if (s.getName() != null && s.getName().toLowerCase().contains(needle)) {
                results.add(s);
            }
        }
        return results;
    }

    @Override
    public List<Services> findByPriceBetween(BigDecimal min, BigDecimal max) {
        List<Services> results = new ArrayList<>();
        for (Services s : servicesMap.values()) {
            if (s.getPrice() != null &&
                    s.getPrice().compareTo(min) >= 0 &&
                    s.getPrice().compareTo(max) <= 0) {
                results.add(s);
            }
        }
        return results;
    }

    @Override
    public List<Services> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Services> findAll(Pageable pageable) {
        return null;
    }
}
