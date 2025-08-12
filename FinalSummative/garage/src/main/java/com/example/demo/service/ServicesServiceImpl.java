package com.example.demo.service;

import com.example.demo.model.Services;
import com.example.demo.repository.ServicesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository repo;

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
        return repo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Services updateService(int id, Services updatedService) {
        return repo.findById(id).map(existing -> {
            existing.setName(updatedService.getName());
            existing.setDescription(updatedService.getDescription());
            existing.setPrice(updatedService.getPrice());
            return repo.save(existing);
        }).orElse(null);
    }

    @Override
    public boolean deleteService(int id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
