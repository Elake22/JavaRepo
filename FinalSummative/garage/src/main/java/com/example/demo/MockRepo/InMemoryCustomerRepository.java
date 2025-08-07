package com.example.demo.MockRepo;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

// This mock repo stores customers in memory for testing purposes
@Repository
@Profile("mock")
public class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<Integer, Customer> customerMap = new HashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    @Override
    public Customer save(Customer customer) {
        customer.setId(idGenerator.getAndIncrement());
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