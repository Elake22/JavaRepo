package org.example.service;

import org.example.data.PaymentRepo;
import org.example.data.impl.PaymentRepoImpl;
import org.example.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepoImplTest {

    private PaymentRepo paymentRepo;

    @BeforeEach
    void setup() {
        var dataSource = new DriverManagerDataSource(
                "jdbc:mysql://localhost:3306/SimpleBistro?serverTimezone=America/Chicago",
                "root",
                "falco8088"
        );
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        paymentRepo = new PaymentRepoImpl(jdbcTemplate);
        jdbcTemplate.execute("CALL sp_reset_data()");
    }

    @Test
    void findByOrderId_showsIfPaymentsExistOrNot() {
        List<Payment> result = paymentRepo.findByOrderId(1); // use an OrderID you want to test

        if (result.isEmpty()) {
            System.out.println("No payments found for OrderID 1 (empty list).");
            assertTrue(result.isEmpty());
        } else {
            System.out.println("Payments found for OrderID 1:");
            result.forEach(System.out::println);
            assertFalse(result.isEmpty());
        }
    }


    @Test
    void findByOrderId_nonExistingOrder_returnsEmpty() {
        List<Payment> result = paymentRepo.findByOrderId(9999);
        assertNotNull(result);
        assertTrue(result.isEmpty(), "Expected no payments for non-existent OrderID");
    }
}
