package com.example.demo.controller;

import com.example.demo.model.Services;
import com.example.demo.service.ServicesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServicesController {

    private final ServicesService servicesService;

    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    // GET all services
    @GetMapping
    public ResponseEntity<List<Services>> getAllServices() {
        return ResponseEntity.ok(servicesService.getAllServices());
    }

    // GET service by ID
    @GetMapping("/{id}")
    public ResponseEntity<Services> getServiceById(@PathVariable int id) {
        Services s = servicesService.getServiceById(id);
        return s != null ? ResponseEntity.ok(s) : ResponseEntity.notFound().build();
    }

    // POST a new service
    @PostMapping
    public ResponseEntity<Services> addService(@RequestBody Services service) {
        Services saved = servicesService.addService(service);
        return ResponseEntity.ok(saved);
    }

    // PUT to update a service
    @PutMapping("/{id}")
    public ResponseEntity<Services> updateService(@PathVariable int id, @RequestBody Services updatedService) {
        Services updated = servicesService.updateService(id, updatedService);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // DELETE a service
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable int id) {
        boolean deleted = servicesService.deleteService(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
