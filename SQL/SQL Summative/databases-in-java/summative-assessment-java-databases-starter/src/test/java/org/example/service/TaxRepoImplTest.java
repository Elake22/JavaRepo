package org.example.service;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.impl.TaxRepoImpl;
import org.example.model.Tax;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Optional;

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

        jdbcTemplate.update("""
        INSERT INTO Tax (TaxID, TaxPercentage, StartDate, EndDate)
        VALUES (?, ?, ?, ?)
        ON DUPLICATE KEY UPDATE TaxPercentage = VALUES(TaxPercentage)
    """, 1, new java.math.BigDecimal("0.08"), java.sql.Date.valueOf("2020-01-01"), null);

    }


    @Test
    void canFetchTaxes() {
        var taxes = taxRepo.findAll();
        assertNotNull(taxes);
        assertFalse(taxes.isEmpty(), "Taxes list should not be empty");
        taxes.forEach(System.out::println);
    }
    @Test
    void findById_existingId_returnsTax() throws Exception {
        Optional<Tax> result = taxRepo.findById(1); // Adjust ID to one that exists in your test DB
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getTaxID());
    }

    @Test
    void findById_nonExistingId_returnsEmpty() throws Exception {
        Optional<Tax> result = taxRepo.findById(9999); // ID that does NOT exist
        assertFalse(result.isPresent());
    }
    @Test
    void createTax_validData_savesAndReturnsTax() throws Exception {
        Tax saved = new Tax();
        saved.setTaxPercentage(new BigDecimal("0.075"));
        saved.setStartDate(LocalDate.of(2023, 1, 1));

        Tax created = taxRepo.create(saved);

        assertNotNull(created);
        assertTrue(created.getTaxID() > 0);

        Optional<Tax> fromDb = taxRepo.findById(created.getTaxID());
        assertTrue(fromDb.isPresent());

        // Adjust expected to match DB rounding (DECIMAL(5,2))
        BigDecimal expected = saved.getTaxPercentage().setScale(2, RoundingMode.HALF_UP); // rounds 0.075 to 0.08
        BigDecimal actual = fromDb.get().getTaxPercentage().setScale(2, RoundingMode.HALF_UP);

        assertEquals(0, expected.compareTo(actual), "Tax percentages should match with scale 2");
    }


}

