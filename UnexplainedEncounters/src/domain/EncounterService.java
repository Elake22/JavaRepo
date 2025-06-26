package domain;

import data.EncounterFileRepository;

import java.util.List;

public class EncounterService {
    private final EncounterFileRepository repository;

    public EncounterService(EncounterFileRepository repository) {
        this.repository = repository;
    }

    public List<Encounter> findByType(EncounterType type) {
        return repository.findByType(type);
    }

    public Encounter findById(int id) {
        return repository.findById(id);
    }

    public List<Encounter> findAll() {
        return repository.findAll();
    }
    public boolean update(Encounter updated) {
        // simple logic for now — find by ID, if exists, update it
        Encounter original = repository.findById(updated.getId());
        if (original == null) {
            return false;
        }
        // update by replacing
        return repository.update(updated);
    }
    public boolean deleteById(int id) {
        return repository.deleteById(id);
    }
    public boolean add(Encounter encounter) {
        return repository.add(encounter);
    }


}
