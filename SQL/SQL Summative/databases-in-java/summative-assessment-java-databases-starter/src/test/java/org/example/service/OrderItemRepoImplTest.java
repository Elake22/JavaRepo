package org.example.service;

import org.example.data.impl.OrderItemRepoImpl;
import org.example.model.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderItemRepoImplTest {

    private OrderItemRepoImpl orderItemRepo;

    @BeforeEach
    void setup() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/SimpleBistro?serverTimezone=America/Chicago");
        dataSource.setUsername("root");
        dataSource.setPassword("falco8088");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        orderItemRepo = new OrderItemRepoImpl(jdbcTemplate);
    }

    @Test
    void findByOrderId_showIfItemsExist() throws Exception {
        int testOrderId = 1;
        List<OrderItem> result = orderItemRepo.findByOrderId(testOrderId);

        if (result.isEmpty()) {
            System.out.println("❌ No items found for OrderID " + testOrderId);
        } else {
            System.out.println("✅ Found " + result.size() + " item(s) for OrderID " + testOrderId);
            result.forEach(System.out::println); // Optional: print details
        }
    }

    @Test
    void findByOrderId_nonExistingOrder_returnsEmptyList() throws Exception {
        List<OrderItem> items = orderItemRepo.findByOrderId(9999);
        assertNotNull(items);
        assertTrue(items.isEmpty());
    }
}
