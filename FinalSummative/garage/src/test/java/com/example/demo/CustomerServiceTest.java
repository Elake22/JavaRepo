package com.example.demo;

import com.example.demo.MockRepo.InMemoryCustomerRepository;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import com.example.demo.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceTest {

    private InMemoryCustomerRepository repo;
    private CustomerService service;

    @BeforeEach
    void setUp() {
        repo = new InMemoryCustomerRepository();
        // InMemoryCustomerRepository preloads sample data; clear for predictable tests
        repo.deleteAll(); // if your repo doesn't have this, use repo.clear() or add a deleteAll() helper
        service = new CustomerServiceImpl(repo);
    }

    // --- helper to build a Customer via setters  ---
    private static Customer makeCustomer(String first, String last, String phone, String email, String carModel) {
        Customer c = new Customer();
        c.setFirstName(first);
        c.setLastName(last);
        c.setPhone(phone);
        c.setEmail(email);
        c.setCarModel(carModel);
        return c;
    }

    @Test
    void testAddCustomerSuccess() {
        Customer customer = makeCustomer("Kanesha", "Lake", "1234567890", "k@example.com", "Civic");
        Customer added = service.addCustomer(customer);

        assertNotNull(added);
        assertNotNull(added.getId());
        assertEquals("Kanesha", added.getFirstName());
        assertEquals("k@example.com", added.getEmail()); // service lowercases/normalizes
        assertEquals("Civic", added.getCarModel());
    }

    @Test
    void testGetAllCustomers() {
        service.addCustomer(makeCustomer("Alpha", "Bravo", "111", "a@example.com", null));
        service.addCustomer(makeCustomer("Charlie", "Delta", "222", "c@example.com", null));

        List<Customer> customers = service.getAllCustomers();
        assertEquals(2, customers.size());
    }

    @Test
    void testGetCustomerByIdNotFound() {
        Customer result = service.getCustomerById(999);
        assertNull(result);
    }

    @Test
    void testUpdateCustomerSuccess() {
        Customer created = service.addCustomer(makeCustomer("Update", "Me", "333", "up@example.com", "Accord"));
        Integer id = created.getId();

        // Only the fields we set will be applied by updateCustomer
        Customer patch = new Customer();
        patch.setFirstName("Updated");
        patch.setLastName("Name");
        patch.setPhone("444");
        patch.setEmail("new@example.com");     // will be normalized to lowercase by service
        patch.setCarModel("Civic Type R");

        Customer result = service.updateCustomer(id, patch);

        assertNotNull(result);
        assertEquals("Updated", result.getFirstName());
        assertEquals("Name", result.getLastName());
        assertEquals("444", result.getPhone());
        assertEquals("new@example.com", result.getEmail());
        assertEquals("Civic Type R", result.getCarModel());
    }

    @Test
    void testUpdateCustomerFail_NotFound() {
        Customer patch = new Customer();
        patch.setFirstName("Ghost");
        patch.setEmail("ghost@example.com");
        Customer result = service.updateCustomer(99, patch);
        assertNull(result);
    }

    @Test
    void testDeleteCustomerSuccess() {
        Customer created = service.addCustomer(makeCustomer("Delete", "This", "555", "del@example.com", null));
        boolean deleted = service.deleteCustomer(created.getId());
        assertTrue(deleted);
        assertNull(service.getCustomerById(created.getId()));
    }

    @Test
    void testDeleteCustomerFail_NotFound() {
        boolean deleted = service.deleteCustomer(404);
        assertFalse(deleted);
    }

    @Test
    void testAddCustomerDuplicateEmailUpdatesExisting() {
        Customer first = service.addCustomer(makeCustomer("Jane", "Doe", "111", "dup@example.com", "Model 3"));
        // same email, different name/phone — should update existing not create new
        Customer second = service.addCustomer(makeCustomer("Janet", "D.", "222", "dup@example.com", "Model Y"));

        assertEquals(first.getId(), second.getId()); // same record updated
        assertEquals("Janet", second.getFirstName());
        assertEquals("222", second.getPhone());
        assertEquals("Model Y", second.getCarModel());
        assertEquals(1, service.getAllCustomers().size()); // still a single row
    }
}
