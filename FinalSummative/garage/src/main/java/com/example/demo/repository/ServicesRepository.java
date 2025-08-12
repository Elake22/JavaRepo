package com.example.demo.repository;

import com.example.demo.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ServicesRepository extends JpaRepository<Services, Integer> {

    // Derived query methods
    List<Services> findByNameContainingIgnoreCase(String namePart);

    List<Services> findByPriceBetween(BigDecimal min, BigDecimal max);
}

// JpaRepository<Services, Integer> already provides: save(entity), findById(id), findAll(), deleteById(id), existsById(id), paging/sorting, etc.