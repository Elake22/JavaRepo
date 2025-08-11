package com.example.demo;

import com.example.demo.MockRepo.InMemoryServicesRepository;
import com.example.demo.model.Services;
import com.example.demo.service.ServicesService;
import com.example.demo.service.ServicesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class ServicesServiceTest {

    private ServicesService service;

    @BeforeEach
    void setUp() {
        service = new ServicesServiceImpl(new InMemoryServicesRepository());
    }
    @Test
    void testAddServiceSuccess() {
        Services s = new Services(0, "Tire Rotation", "Rotate tires for even wear", new BigDecimal("29.99"));
        Services added = service.addService(s);
        assertNotNull(added);
        assertEquals("Tire Rotation", added.getName());
    }
    @Test
    void testGetAllServices() {
        service.addService(new Services(0, "Oil Change", "Regular oil changes", new BigDecimal("39.99")));
        service.addService(new Services(0, "Brake Inspection", "Inspect brakes", new BigDecimal("59.99")));
        List<Services> all = service.getAllServices();
        assertEquals(2, all.size());
    }
    @Test
    void testGetServiceByIdNotFound() {
        Services result = service.getServiceById(404);
        assertNull(result);
    }
    @Test
    void testUpdateServiceSuccess() {
        Services original = service.addService(new Services(0, "Battery", "Replace battery", new BigDecimal("99.99")));
        int id = original.getId();
        Services updated = new Services(id, "Battery Replacement", "Premium battery service", new BigDecimal("119.99"));

        Services result = service.updateService(id, updated);
        assertNotNull(result);
        assertEquals("Battery Replacement", result.getName());
        assertEquals(new BigDecimal("119.99"), result.getPrice());
    }
    @Test
    void testUpdateServiceFail_NotFound() {
        Services updated = new Services(999, "Ghost Service", "Does not exist", new BigDecimal("0.00"));
        Services result = service.updateService(999, updated);
        assertNull(result);
    }
    @Test
    void testDeleteServiceSuccess() {
        Services s = service.addService(new Services(0, "A/C Repair", "Fix air conditioning", new BigDecimal("89.99")));
        boolean deleted = service.deleteService(s.getId());
        assertTrue(deleted);
        assertNull(service.getServiceById(s.getId()));
    }
    @Test
    void testDeleteServiceFail_NotFound() {
        boolean result = service.deleteService(999);
        assertFalse(result);
    }

}
