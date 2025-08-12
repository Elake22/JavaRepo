package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repo;

    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public Customer addCustomer(Customer in) {
        if (in == null || !StringUtils.hasText(in.getEmail())) {
            throw new IllegalArgumentException("Email is required");
        }
        final String email = in.getEmail().trim().toLowerCase();

        return repo.findByEmailIgnoreCase(email)
                .map(existing -> {
                    // Update selected fields if provided
                    if (StringUtils.hasText(in.getFirstName())) existing.setFirstName(in.getFirstName());
                    if (StringUtils.hasText(in.getLastName()))  existing.setLastName(in.getLastName());
                    if (StringUtils.hasText(in.getPhone()))      existing.setPhone(in.getPhone());
                    if (in.getCarModel() != null)                existing.setCarModel(in.getCarModel());
                    existing.setEmail(email); // keep normalized
                    return repo.save(existing);
                })
                .orElseGet(() -> {
                    in.setEmail(email); // normalize on create
                    return repo.save(in);
                });
    }

    @Override
    public List<Customer> getAllCustomers() {
        return repo.findAll();
    }

    @Override
    public Customer getCustomerById(int id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Customer updateCustomer(int id, Customer updated) {
        return repo.findById(id).map(existing -> {
            if (StringUtils.hasText(updated.getFirstName())) existing.setFirstName(updated.getFirstName());
            if (StringUtils.hasText(updated.getLastName()))  existing.setLastName(updated.getLastName());
            if (StringUtils.hasText(updated.getEmail()))     existing.setEmail(updated.getEmail().trim().toLowerCase());
            if (StringUtils.hasText(updated.getPhone()))     existing.setPhone(updated.getPhone());
            if (updated.getCarModel() != null)               existing.setCarModel(updated.getCarModel());
            return repo.save(existing);
        }).orElse(null);
    }

    @Override
    public boolean deleteCustomer(int id) {
        if (!repo.existsById(id)) return false;
        repo.deleteById(id);
        return true;
    }
}
