package com.example.demo.repository;

import com.example.demo.model.Mechanic;
import java.util.List;

// Interface to define basic CRUD operations for mechanics
public interface MechanicRepository {

    Mechanic save(Mechanic mechanic);

    List<Mechanic> findAll();

    Mechanic findById(int id);

    Mechanic update(int id, Mechanic mechanic);

    boolean delete(int id);
}
