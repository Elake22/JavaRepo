package org.example.service;

import org.example.data.impl.TaxRepoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.jupiter.api.Assertions.*;

public class TaxRepoImplTest {

    private TaxRepoImpl taxRepo;

    @BeforeEach
    void setup() {
        // Create DataSource manually
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/SimpleBistro?serverTimezone=America/Chicago");
        dataSource.setUsername("root"); // change if needed
        dataSource.setPassword("falco8088"); // change if needed
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // Create JdbcTemplate manually
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // Create your repository (assuming it takes JdbcTemplate)
        taxRepo = new TaxRepoImpl(jdbcTemplate);

        // Optional: reset test data
        jdbcTemplate.execute("CALL sp_reset_data()");
    }

    @Test
    void canFetchTaxes() {
        var taxes = taxRepo.findAll();
        assertNotNull(taxes);
        assertFalse(taxes.isEmpty(), "Taxes list should not be empty");
        taxes.forEach(System.out::println);
    }
}
