package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

import java.util.List;


// Implementation of the CustomerService interface
    public class CustomerServiceImpl implements CustomerService {

        private final CustomerRepository customerRepository;

        // Constructor injection of repository dependency
        public CustomerServiceImpl(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }

        // Delegates to repository to add customer
        @Override
        public Customer addCustomer(Customer customer) {
            return customerRepository.save(customer);
        }

        // Fetches all customers from repository
        @Override
        public List<Customer> getAllCustomers() {
            return customerRepository.findAll();
        }

        // Retrieves one customer by ID
        @Override
        public Customer getCustomerById(int id) {
            return customerRepository.findById(id);
        }

        // Updates customer by ID (if exists)
        @Override
        public Customer updateCustomer(int id, Customer updatedCustomer) {
            return customerRepository.update(id, updatedCustomer);
        }

        // Removes customer by ID (returns true if successful)
        @Override
        public boolean deleteCustomer(int id) {
            return customerRepository.delete(id);
        }
    }

