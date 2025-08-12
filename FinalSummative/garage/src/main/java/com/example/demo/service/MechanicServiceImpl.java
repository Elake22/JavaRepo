package com.example.demo.service;

import com.example.demo.model.Mechanic;
import com.example.demo.repository.MechanicRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MechanicServiceImpl implements MechanicService {

    private final MechanicRepository repo;

    public MechanicServiceImpl(MechanicRepository repo) {
        this.repo = repo;
    }

    @Override
    public Mechanic addMechanic(Mechanic mechanic) {
        return repo.save(mechanic);
    }

    @Override
    public List<Mechanic> getAllMechanics() {
        return repo.findAll();
    }

    @Override
    public Mechanic getMechanicById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Mechanic updateMechanic(int id, Mechanic updated) {
        return repo.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setSpecialty(updated.getSpecialty());
            existing.setYearsExperience(updated.getYearsExperience());
            return repo.save(existing);
        }).orElse(null);
    }

    @Override
    public boolean deleteMechanic(int id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
