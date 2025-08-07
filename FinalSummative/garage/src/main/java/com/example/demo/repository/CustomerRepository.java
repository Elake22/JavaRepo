package com.example.demo.repository;

import com.example.demo.model.Customer;
import java.util.List;

// Interface to define basic CRUD operations for customers
public interface CustomerRepository {

    // Save a new customer
    Customer save(Customer customer);

    // Find all customers
    List<Customer> findAll();

    // Find a customer by ID
    Customer findById(int id);

    // Update a customer by ID
    Customer update(int id, Customer updatedCustomer);

    // Delete a customer by ID
    boolean delete(int id);
}
