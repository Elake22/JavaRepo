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

    private InMemoryMechanicRepository repo;
    private MechanicService service;

    @BeforeEach
    void setUp() {
        repo = new InMemoryMechanicRepository();
        // Repo preloads sample data; clear for predictable tests
        repo.deleteAll();
        service = new MechanicServiceImpl(repo);
    }

    @Test
    void testAddMechanicSuccess() {
        Mechanic m = new Mechanic(null, "John Doe", "Engine Specialist", 15);
        Mechanic added = service.addMechanic(m);
        assertNotNull(added.getId());
        assertEquals("John Doe", added.getName());
        assertEquals("Engine Specialist", added.getSpecialty());
        assertEquals(15, added.getYearsExperience());
    }

    @Test
    void testGetAllMechanics() {
        service.addMechanic(new Mechanic(null, "Laura Chen", "Brake Systems", 8));
        service.addMechanic(new Mechanic(null, "Mike Rodriguez", "Transmission Expert", 10));
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
        Mechanic m = service.addMechanic(new Mechanic(null, "David Wilson", "A/C and Cooling", 14));
        Integer id = m.getId();
        Mechanic updated = new Mechanic(id, "David Wilson", "A/C and Cooling", 20);

        Mechanic result = service.updateMechanic(id, updated);
        assertNotNull(result);
        assertEquals("David Wilson", result.getName());
        assertEquals("A/C and Cooling", result.getSpecialty());
        assertEquals(20, result.getYearsExperience());
    }

    @Test
    void testUpdateMechanicFail_NotFound() {
        Mechanic updated = new Mechanic(404, "Ghost Tech", "Unknown", 99);
        Mechanic result = service.updateMechanic(404, updated);
        assertNull(result);
    }

    @Test
    void testDeleteMechanicSuccess() {
        Mechanic m = service.addMechanic(new Mechanic(null, "Sarah Johnson", "Electrical Systems", 12));
        boolean deleted = service.deleteMechanic(m.getId());
        assertTrue(deleted);
        assertNull(service.getMechanicById(m.getId()));
    }

    @Test
    void testDeleteMechanicFail_NotFound() {
        boolean deleted = service.deleteMechanic(999);
        assertFalse(deleted);
    }
}
