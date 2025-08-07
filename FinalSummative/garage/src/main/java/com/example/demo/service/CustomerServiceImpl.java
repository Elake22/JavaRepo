package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


// Implementation of the CustomerService interface
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

        // Delegates to repository to add customer
        @Override
        public Customer addCustomer(Customer customer) {
            return repo.save(customer);
        }

        // Fetches all customers from repository
        @Override
        public List<Customer> getAllCustomers() {
            return repo.findAll();
        }

        // Retrieves one customer by ID
        @Override
        public Customer getCustomerById(int id) {
            return repo.findById(id);
        }

        // Updates customer by ID (if exists)
        @Override
        public Customer updateCustomer(int id, Customer updatedCustomer) {
            return repo.update(id, updatedCustomer);
        }

        // Removes customer by ID (returns true if successful)
        @Override
        public boolean deleteCustomer(int id) {
            return repo.delete(id);
        }
    }

