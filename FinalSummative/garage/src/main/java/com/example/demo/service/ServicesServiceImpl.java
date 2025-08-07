package com.example.demo.service;

import com.example.demo.model.Services;
import com.example.demo.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Implementation of ServiceService
@Service
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository repo;

    @Autowired
    public ServicesServiceImpl(ServicesRepository repo) {
        this.repo = repo;
    }

    @Override
    public Services addService(Services service) {
        return repo.save(service);
    }

    @Override
    public List<Services> getAllServices() {
        return repo.findAll();
    }

    @Override
    public Services getServiceById(int id) {
        return repo.findById(id);
    }

    @Override
    public Services updateService(int id, Services updatedService) {
        return repo.update(id, updatedService);
    }

    @Override
    public boolean deleteService(int id) {
        return repo.delete(id);
    }
}
