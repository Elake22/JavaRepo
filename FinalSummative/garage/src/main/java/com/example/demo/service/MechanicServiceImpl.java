package com.example.demo.service;

import com.example.demo.model.Mechanic;
import com.example.demo.repository.MechanicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Implementation of MechanicService
@Service
public class MechanicServiceImpl implements MechanicService {

    private final MechanicRepository repo;

    @Autowired
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
        return repo.findById(id);
    }

    @Override
    public Mechanic updateMechanic(int id, Mechanic updatedMechanic) {
        return repo.update(id, updatedMechanic);
    }

    @Override
    public boolean deleteMechanic(int id) {
        return repo.delete(id);
    }
}
