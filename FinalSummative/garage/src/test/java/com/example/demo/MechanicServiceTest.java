package com.example.demo;

import com.example.demo.MockRepo.InMemoryMechanicRepository;
import com.example.demo.model.Mechanic;
import com.example.demo.service.MechanicService;
import com.example.demo.service.MechanicServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MechanicServiceTest {

    private MechanicService service;

    @BeforeEach
    void setUp() {
        service = new MechanicServiceImpl(new InMemoryMechanicRepository());
    }
    @Test
    void testAddMechanicSuccess() {
        Mechanic m = new Mechanic(0, "John Doe", 15);
        Mechanic added = service.addMechanic(m);
        assertEquals("John Doe", added.getName());
    }
    @Test
    void testGetAllMechanics() {
        service.addMechanic(new Mechanic(0, "Laura Chen", 8));
        service.addMechanic(new Mechanic(0, "Mike Rodriguez", 10));
        List<Mechanic> mechanics = service.getAllMechanics();
        assertEquals(2, mechanics.size());
    }
    @Test
    void testGetMechanicByIdNotFound() {
        Mechanic result = service.getMechanicById(999);
        assertNull(result);
    }
    @Test
    void testUpdateMechanicSuccess() {
        Mechanic m = service.addMechanic(new Mechanic(0, "David Wilson", 14));
        int id = m.getId();
        Mechanic updated = new Mechanic(id, "David Wilson", 20);

        Mechanic result = service.updateMechanic(id, updated);
        assertNotNull(result);
        assertEquals(20, result.getExperienceYears());
    }
    @Test
    void testUpdateMechanicFail_NotFound() {
        Mechanic updated = new Mechanic(404, "Ghost Tech", 99);
        Mechanic result = service.updateMechanic(404, updated);
        assertNull(result);
    }

    @Test
    void testDeleteMechanicSuccess() {
        Mechanic m = service.addMechanic(new Mechanic(0, "Sarah Johnson", 12));
        boolean deleted = service.deleteMechanic(m.getId());
        assertTrue(deleted);
        assertNull(service.getMechanicById(m.getId()));
    }

    @Test
    void testDeleteMechanicFail_NotFound() {
        boolean deleted = service.deleteMechanic(999); // Invalid ID
        assertFalse(deleted);
    }
}
