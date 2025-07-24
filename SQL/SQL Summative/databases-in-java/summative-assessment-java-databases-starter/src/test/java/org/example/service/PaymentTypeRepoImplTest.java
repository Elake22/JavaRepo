package org.example.service;

import org.example.data.PaymentTypeRepo;
import org.example.data.impl.PaymentTypeRepoImpl;
import org.example.model.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTypeRepoImplTest {

    private PaymentTypeRepo paymentTypeRepo;

    @BeforeEach
    void setup() {
        var dataSource = new DriverManagerDataSource(
                "jdbc:mysql://localhost:3306/SimpleBistro?serverTimezone=America/Chicago",
                "root",
                "falco8088"
        );
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        paymentTypeRepo = new PaymentTypeRepoImpl(jdbcTemplate);
        jdbcTemplate.execute("CALL sp_reset_data()");
    }

    @Test
    void findById_existingId_returnsPaymentType() {
        Optional<PaymentType> result = paymentTypeRepo.findById(1); // Ensure ID 1 exists in test DB
        assertTrue(result.isPresent(), "PaymentType should exist for ID 1");
    }

    @Test
    void findById_nonExistingId_returnsEmpty() {
        Optional<PaymentType> result = paymentTypeRepo.findById(9999);
        assertTrue(result.isEmpty(), "Expected no PaymentType for non-existent ID");
    }

    @Test
    void findAll_returnsListOfPaymentTypes() {
        var result = paymentTypeRepo.getAll();
        assertNotNull(result);
        assertFalse(result.isEmpty(), "Expected non-empty list of payment types");
    }
}
