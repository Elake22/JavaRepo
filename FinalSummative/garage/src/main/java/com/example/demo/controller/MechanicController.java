package com.example.demo.controller;

import com.example.demo.model.Mechanic;
import com.example.demo.service.MechanicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mechanics")
public class MechanicController {

    private final MechanicService mechanicService;

    @Autowired
    public MechanicController(MechanicService mechanicService) {
        this.mechanicService = mechanicService;
    }

    // GET all mechanics
    @GetMapping
    public List<Mechanic> getAllMechanics() {
        return mechanicService.getAllMechanics();
    }

    // GET mechanic by ID
    @GetMapping("/{id}")
    public Mechanic getMechanicById(@PathVariable int id) {
        return mechanicService.getMechanicById(id);
    }

    // POST a new mechanic
    @PostMapping
    public Mechanic addMechanic(@RequestBody Mechanic mechanic) {
        return mechanicService.addMechanic(mechanic);
    }

    // PUT to update an existing mechanic
    @PutMapping("/{id}")
    public Mechanic updateMechanic(@PathVariable int id, @RequestBody Mechanic updatedMechanic) {
        return mechanicService.updateMechanic(id, updatedMechanic);
    }

    // DELETE mechanic by ID
    @DeleteMapping("/{id}")
    public boolean deleteMechanic(@PathVariable int id) {
        return mechanicService.deleteMechanic(id);
    }
}
