package org.example.service;

import org.example.data.ItemCategoryRepo;
import org.example.data.impl.ItemCategoryRepoImpl;
import org.example.model.ItemCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ItemCategoryRepoImplTest {

    private ItemCategoryRepo itemCategoryRepo;

    @BeforeEach
    void setup() {
        var dataSource = new DriverManagerDataSource(
                "jdbc:mysql://localhost:3306/SimpleBistro?serverTimezone=America/Chicago",
                "root",
                "falco8088"
        );
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        itemCategoryRepo = new ItemCategoryRepoImpl(jdbcTemplate);
        jdbcTemplate.execute("CALL sp_reset_data()");
    }

    @Test
    void findById_existingId_returnsItemCategory() {
        Optional<ItemCategory> result = itemCategoryRepo.findById(1);
        assertTrue(result.isPresent(), "ItemCategory should exist for ID 1");
    }

    @Test
    void findById_nonExistingId_returnsEmpty() {
        Optional<ItemCategory> result = itemCategoryRepo.findById(9999);
        assertTrue(result.isEmpty(), "Expected no ItemCategory for non-existent ID");
    }
}
