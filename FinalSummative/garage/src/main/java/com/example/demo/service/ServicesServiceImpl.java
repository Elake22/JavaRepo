package com.example.demo.service;

import com.example.demo.model.Services;
import com.example.demo.repository.ServicesRepository;

import java.util.List;

// Implementation of ServiceService
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepository serviceRepository;

    public ServicesServiceImpl(ServicesRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Services addService(Services service) {
        return serviceRepository.save(service);
    }

    @Override
    public List<Services> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Services getServiceById(int id) {
        return serviceRepository.findById(id);
    }

    @Override
    public Services updateService(int id, Services updatedService) {
        return serviceRepository.update(id, updatedService);
    }

    @Override
    public boolean deleteService(int id) {
        return serviceRepository.delete(id);
    }
}
