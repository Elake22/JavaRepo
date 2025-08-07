package com.example.demo.service;

import com.example.demo.model.Services;
import java.util.List;

// Handles business logic related to garage services
public interface ServicesService {

    Services addService(Services service);

    List<Services> getAllServices();

    Services getServiceById(int id);

    Services updateService(int id, Services updatedService);

    boolean deleteService(int id);
}
