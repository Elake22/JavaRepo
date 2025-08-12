package com.example.demo.repository;

import com.example.demo.model.Mechanic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MechanicRepository extends JpaRepository<Mechanic, Integer> {

    // Optional extra finder methods
    List<Mechanic> findBySpecialtyContainingIgnoreCase(String specialty);

    List<Mechanic> findByYearsExperienceGreaterThanEqual(int minYears);
}
