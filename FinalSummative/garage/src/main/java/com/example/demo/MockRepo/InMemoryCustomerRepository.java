package com.example.demo.MockRepo;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// This mock repo stores customers in memory for testing purposes
public class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<Integer, Customer> customerMap = new HashMap<>();
    private int currentId = 1;

    @Override
    public Customer save(Customer customer) {
        // Assign a new ID and save the customer
        customer.setId(currentId++);
        customerMap.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer findById(int id) {
        return customerMap.get(id);
    }

    @Override
    public Customer update(int id, Customer updatedCustomer) {
        if (customerMap.containsKey(id)) {
            updatedCustomer.setId(id);
            customerMap.put(id, updatedCustomer);
            return updatedCustomer;
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return customerMap.remove(id) != null;
    }
}
