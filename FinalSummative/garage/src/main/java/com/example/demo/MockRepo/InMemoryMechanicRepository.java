package com.example.demo.MockRepo;

import com.example.demo.model.Mechanic;
import com.example.demo.repository.MechanicRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Repository
@Profile("mock")
public class InMemoryMechanicRepository implements MechanicRepository {

    private final Map<Integer, Mechanic> mechanicMap = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public InMemoryMechanicRepository() {
        initializeSampleData();
    }

    private void initializeSampleData() {
        // name, specialty, yearsExperience (ids assigned on save)
        save(new Mechanic(null, "John Smith", "Engine Specialist", 15));
        save(new Mechanic(null, "Sarah Johnson", "Electrical Systems", 12));
        save(new Mechanic(null, "Mike Rodriguez", "Transmission Expert", 10));
        save(new Mechanic(null, "Laura Chen", "Brake Systems", 8));
        save(new Mechanic(null, "David Wilson", "A/C and Cooling", 14));
    }

    // ---------- JpaRepository core methods ----------

    @Override
    public <S extends Mechanic> S save(S mechanic) {
        if (mechanic.getId() == null) {
            mechanic.setId(idGenerator.getAndIncrement());
        }
        mechanicMap.put(mechanic.getId(), mechanic);
        return mechanic;
    }

    @Override
    public Optional<Mechanic> findById(Integer id) {
        return Optional.ofNullable(mechanicMap.get(id));
    }

    @Override
    public List<Mechanic> findAll() {
        return new ArrayList<>(mechanicMap.values());
    }

    @Override
    public boolean existsById(Integer id) {
        return mechanicMap.containsKey(id);
    }

    @Override
    public long count() {
        return mechanicMap.size();
    }

    @Override
    public void deleteById(Integer id) {
        mechanicMap.remove(id);
    }

    @Override
    public void delete(Mechanic entity) {
        if (entity.getId() != null) {
            mechanicMap.remove(entity.getId());
        }
    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Mechanic> entities) {

    }

    @Override
    public void deleteAll() {
        mechanicMap.clear();
    }

    // ---------- Convenience implementations for the rest ----------

    @Override
    public List<Mechanic> findAllById(Iterable<Integer> ids) {
        List<Mechanic> list = new ArrayList<>();
        for (Integer id : ids) {
            Mechanic m = mechanicMap.get(id);
            if (m != null) list.add(m);
        }
        return list;
    }

    @Override
    public <S extends Mechanic> List<S> saveAll(Iterable<S> entities) {
        List<S> saved = new ArrayList<>();
        for (S e : entities) saved.add(save(e));
        return saved;
    }

    @Override public void flush() {}
    @Override public <S extends Mechanic> S saveAndFlush(S entity) { return save(entity); }

    @Override
    public <S extends Mechanic> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override public void deleteAllInBatch() { mechanicMap.clear(); }
    @Override public void deleteAllInBatch(Iterable<Mechanic> entities) { deleteAll(entities); }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override public Mechanic getOne(Integer id) { return mechanicMap.get(id); }
    @Override public Mechanic getById(Integer id) { return mechanicMap.get(id); }

    @Override
    public Mechanic getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Mechanic> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Mechanic> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Mechanic> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Mechanic> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Mechanic> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Mechanic> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Mechanic, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    // ---------- Query methods declared on MechanicRepository ----------

    @Override
    public List<Mechanic> findBySpecialtyContainingIgnoreCase(String specialty) {
        String needle = specialty == null ? "" : specialty.toLowerCase();
        List<Mechanic> results = new ArrayList<>();
        for (Mechanic m : mechanicMap.values()) {
            if (m.getSpecialty() != null && m.getSpecialty().toLowerCase().contains(needle)) {
                results.add(m);
            }
        }
        return results;
    }

    @Override
    public List<Mechanic> findByYearsExperienceGreaterThanEqual(int minYears) {
        List<Mechanic> results = new ArrayList<>();
        for (Mechanic m : mechanicMap.values()) {
            if (m.getYearsExperience() >= minYears) results.add(m);
        }
        return results;
    }

    @Override
    public List<Mechanic> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Mechanic> findAll(Pageable pageable) {
        return null;
    }
}
