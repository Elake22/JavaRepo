package com.example.demo.repository;

import com.example.demo.model.Services;
import java.util.List;

// Interface for CRUD operations on garage services
public interface ServicesRepository {

    Services save(Services services);

    List<Services> findAll();

    Services findById(int id);

    Services update(int id, Services updatedServices);

    boolean delete(int id);
}
