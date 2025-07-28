package org.example.service;

import org.example.data.impl.ItemRepoImpl;
import org.example.model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ItemRepoImplTest {

    private ItemRepoImpl itemRepo;

    @BeforeEach
    void setup() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/SimpleBistro?serverTimezone=America/Chicago");
        dataSource.setUsername("root");
        dataSource.setPassword("falco8088");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("CALL sp_reset_data()");

        itemRepo = new ItemRepoImpl(jdbcTemplate);
    }

    @Test
    void findAll_returnsItemList() throws Exception {
        var items = itemRepo.findAll();
        assertNotNull(items);
        assertFalse(items.isEmpty());
    }

    @Test
    void findById_existingId_returnsItem() throws Exception {
        Optional<Item> result = itemRepo.findById(1);
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getItemID());
    }

    @Test
    void findById_nonExistingId_returnsEmpty() throws Exception {
        Optional<Item> result = itemRepo.findById(9999);
        assertFalse(result.isPresent());
    }
    @Test
    void getItemsByCategory_validInput_returnsItemsInCategory() throws Exception {
        var today = java.time.LocalDate.now();
        var items = itemRepo.getItemsByCategory(today, 1);
        assertNotNull(items);
        assertFalse(items.isEmpty());
        assertEquals(1, items.get(0).getItemCategoryID());
    }
    @Test
    void findByCategoryId_returnsCorrectItems() throws Exception {
        var items = itemRepo.findByCategoryId(1);
        assertNotNull(items);
        assertTrue(items.stream().allMatch(i -> i.getItemCategoryID() == 1));
    }


}
