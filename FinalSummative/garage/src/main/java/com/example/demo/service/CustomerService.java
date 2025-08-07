package com.example.demo.service;
import com.example.demo.model.Customer;

import java.util.List;

public interface CustomerService {

    // Add a new customer
    Customer addCustomer(Customer customer);

    // Get all customers
    List<Customer> getAllCustomers();

    // Get customer by ID
    Customer getCustomerById(int id);

    // Update a customer
    Customer updateCustomer(int id, Customer updatedCustomer);

    // Delete a customer
    boolean deleteCustomer(int id);
}

