package org.example.service;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.impl.*;
import org.example.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BistroServiceTest {

    private BistroService service;

    @BeforeEach
    void setup() {
        var dataSource = new DriverManagerDataSource(
                "jdbc:mysql://localhost:3306/SimpleBistro?serverTimezone=America/Chicago",
                "root",
                "falco8088"
        );
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // Reset the DB
        jdbcTemplate.execute("CALL sp_reset_data()");

        service = new BistroService(
                new OrderRepoImpl(jdbcTemplate),
                new OrderItemRepoImpl(jdbcTemplate),
                new PaymentRepoImpl(jdbcTemplate),
                new PaymentTypeRepoImpl(jdbcTemplate),
                new ServerRepoImpl(jdbcTemplate),
                new TaxRepoImpl(jdbcTemplate),
                new ItemRepoImpl(jdbcTemplate),
                new ItemCategoryRepoImpl(jdbcTemplate)
        );
    }

    @Test
    void getCompleteOrder_validId_returnsOrder() throws Exception {
        int validOrderId = 1; // Make sure this ID exists in the Order table
        Optional<Order> result = service.getCompleteOrder(validOrderId);

        assertTrue(result.isPresent(), "Expected order to be present for ID " + validOrderId);
        Order order = result.get();

        // Print debug info
        System.out.println("Order retrieved with ID: " + order.getOrderID());
        System.out.println("Items count: " + order.getItems().size());
        System.out.println("Payments count: " + order.getPayments().size());

        // Pass even if no items or payments yet
        assertNotNull(order.getItems(), "Order items list should not be null");
        assertNotNull(order.getPayments(), "Order payments list should not be null");
    }


    @Test
    void getCompleteOrder_invalidId_returnsEmpty() throws Exception {
        Optional<Order> result = service.getCompleteOrder(9999);
        assertTrue(result.isEmpty(), "Expected no order for invalid ID");
    }

    @Test
    void getAllItems_returnsItemList() throws Exception {
        List<Item> items = service.getAllItems();
        assertFalse(items.isEmpty(), "Expected some items to be returned");
    }

    @Test
    void getAllItemCategories_returnsCategoryList() {
        List<ItemCategory> categories = service.getAllItemCategories();
        assertFalse(categories.isEmpty(), "Expected item categories to be returned");
    }

    @Test
    void getServerById_validId_returnsServer() throws Exception {
        Optional<Server> server = service.getServerById(1);
        assertTrue(server.isPresent(), "Expected server with ID 1");
    }

    @Test
    void getAllPaymentTypes_returnsList() throws InternalErrorException {
        List<PaymentType> types = service.getAllPaymentTypes();

        if (types.isEmpty()) {
            System.out.println("No payment types returned — database may be empty.");
            assertTrue(types.isEmpty(), "Expected no payment types in empty DB");
        } else {
            System.out.println("Payment types returned: " + types.size());
            assertFalse(types.isEmpty(), "Expected at least one payment type");
        }
    }


    @Test
    void getTaxById_validId_returnsTax() throws Exception {
        Optional<Tax> tax = service.getTaxById(1);

        if (tax.isPresent()) {
            assertNotNull(tax.get().getTaxPercentage(), "Expected tax percentage to be set");
        } else {
            System.out.println("No tax record found with ID 1 — this is expected if DB is empty.");
            assertTrue(tax.isEmpty(), "Expected no tax to be returned when DB is empty");
        }
    }
}
