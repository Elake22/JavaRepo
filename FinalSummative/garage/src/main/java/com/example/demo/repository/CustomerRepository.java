package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Handy finders (optional)
    Optional<Customer> findByEmailIgnoreCase(String email);

    List<Customer> findByLastNameContainingIgnoreCase(String lastNamePart);

    List<Customer> findByPhone(String phone);
}
