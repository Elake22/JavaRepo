package com.example.demo.service;

import com.example.demo.model.Mechanic;
import java.util.List;

// Defines operations for mechanic data
public interface MechanicService {

    Mechanic addMechanic(Mechanic mechanic);

    List<Mechanic> getAllMechanics();

    Mechanic getMechanicById(int id);

    Mechanic updateMechanic(int id, Mechanic updatedMechanic);

    boolean deleteMechanic(int id);
}
