package com.example.demo;

import com.example.demo.model.Customer;
import com.example.demo.MockRepo.InMemoryCustomerRepository;
import com.example.demo.service.CustomerService;
import com.example.demo.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CustomerServiceTest {

    private CustomerService service;

    @BeforeEach
    void setUp() {
        service = new CustomerServiceImpl(new InMemoryCustomerRepository());
    }

    @Test
    void testAddCustomerSuccess() {
        Customer customer = new Customer(0, "Kanesha", "Lake", "k@example.com", "1234567890", "Mustang GT");
        Customer added = service.addCustomer(customer);
        assertNotNull(added);
        assertEquals("Kanesha", added.getFirstName());
    }
    @Test
    void testGetAllCustomers() {
        service.addCustomer(new Customer(0, "Alpha", "Bravo", "a@example.com", "111", "Ford"));
        service.addCustomer(new Customer(0, "Charlie", "Delta", "c@example.com", "222", "Audi"));
        List<Customer> customers = service.getAllCustomers();
        assertEquals(2, customers.size());
    }
    @Test
    void testGetCustomerByIdNotFound() {
        Customer result = service.getCustomerById(999); // ID does not exist
        assertNull(result);
    }
    @Test
    void testUpdateCustomerSuccess() {
        Customer customer = service.addCustomer(new Customer(0, "Update", "Me", "up@example.com", "333", "BMW"));
        int id = customer.getId();
        Customer updated = new Customer(id, "Updated", "Name", "new@example.com", "444", "Audi");

        Customer result = service.updateCustomer(id, updated);
        assertNotNull(result);
        assertEquals("Updated", result.getFirstName());
        assertEquals("new@example.com", result.getEmail());
    }
    @Test
    void testUpdateCustomerFail_NotFound() {
        Customer updated = new Customer(99, "Ghost", "User", "ghost@example.com", "999", "Unknown");
        Customer result = service.updateCustomer(99, updated);
        assertNull(result);
    }
    @Test
    void testDeleteCustomerSuccess() {
        Customer customer = service.addCustomer(new Customer(0, "Delete", "This", "del@example.com", "555", "KIA"));
        boolean deleted = service.deleteCustomer(customer.getId());
        assertTrue(deleted);
        assertNull(service.getCustomerById(customer.getId()));
    }
    @Test
    void testDeleteCustomerFail_NotFound() {
        boolean deleted = service.deleteCustomer(404); // Non-existent ID
        assertFalse(deleted);
    }
}
