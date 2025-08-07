package com.example.demo.service;

import com.example.demo.model.Mechanic;
import com.example.demo.repository.MechanicRepository;

import java.util.List;

// Implementation of MechanicService
public class MechanicServiceImpl implements MechanicService {

    private final MechanicRepository mechanicRepository;

    // Injects the repository into the service
    public MechanicServiceImpl(MechanicRepository mechanicRepository) {
        this.mechanicRepository = mechanicRepository;
    }

    @Override
    public Mechanic addMechanic(Mechanic mechanic) {
        return mechanicRepository.save(mechanic);
    }

    @Override
    public List<Mechanic> getAllMechanics() {
        return mechanicRepository.findAll();
    }

    @Override
    public Mechanic getMechanicById(int id) {
        return mechanicRepository.findById(id);
    }

    @Override
    public Mechanic updateMechanic(int id, Mechanic updatedMechanic) {
        return mechanicRepository.update(id, updatedMechanic);
    }

    @Override
    public boolean deleteMechanic(int id) {
        return mechanicRepository.delete(id);
    }
}
