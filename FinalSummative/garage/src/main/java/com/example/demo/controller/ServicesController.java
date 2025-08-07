package com.example.demo.controller;

import com.example.demo.model.Services;
import com.example.demo.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServicesController {

    private final ServicesService servicesService;

    @Autowired
    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    // GET all services
    @GetMapping
    public List<Services> getAllServices() {
        return servicesService.getAllServices();
    }

    // GET service by ID
    @GetMapping("/{id}")
    public Services getServiceById(@PathVariable int id) {
        return servicesService.getServiceById(id);
    }

    // POST a new service
    @PostMapping
    public Services addService(@RequestBody Services service) {
        return servicesService.addService(service);
    }

    // PUT to update a service
    @PutMapping("/{id}")
    public Services updateService(@PathVariable int id, @RequestBody Services updatedService) {
        return servicesService.updateService(id, updatedService);
    }

    // DELETE a service
    @DeleteMapping("/{id}")
    public boolean deleteService(@PathVariable int id) {
        return servicesService.deleteService(id);
    }
}
