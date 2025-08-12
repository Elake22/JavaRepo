package com.example.demo.controller;

import com.example.demo.model.Mechanic;
import com.example.demo.service.MechanicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mechanics")
public class MechanicController {

    private final MechanicService mechanicService;

    public MechanicController(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    // GET all mechanics
    @GetMapping
    public ResponseEntity<List<Mechanic>> getAllMechanics() {
        return ResponseEntity.ok(mechanicService.getAllMechanics());
    }

    // GET mechanic by ID
    @GetMapping("/{id}")
    public ResponseEntity<Mechanic> getMechanicById(@PathVariable int id) {
        Mechanic mechanic = mechanicService.getMechanicById(id);
        return mechanic != null ? ResponseEntity.ok(mechanic) : ResponseEntity.notFound().build();
    }

    // POST a new mechanic
    @PostMapping
    public ResponseEntity<Mechanic> addMechanic(@RequestBody Mechanic mechanic) {
        Mechanic saved = mechanicService.addMechanic(mechanic);
        return ResponseEntity.ok(saved);
    }

    // PUT to update an existing mechanic
    @PutMapping("/{id}")
    public ResponseEntity<Mechanic> updateMechanic(@PathVariable int id, @RequestBody Mechanic updatedMechanic) {
        Mechanic updated = mechanicService.updateMechanic(id, updatedMechanic);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // DELETE mechanic by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMechanic(@PathVariable int id) {
        boolean deleted = mechanicService.deleteMechanic(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
