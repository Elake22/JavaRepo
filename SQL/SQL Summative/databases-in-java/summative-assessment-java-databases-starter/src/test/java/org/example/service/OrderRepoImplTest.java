package org.example.service;

import org.example.data.OrderRepo;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderRepoImplTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private OrderRepo orderRepo;

    @BeforeEach
    void resetTestData() {
        // Replace with the correct stored procedure if needed
        jdbcTemplate.execute("CALL sp_reset_data();");
    }

    @Test
    void getOrderById_validId_returnsOrder() throws Exception {
        Order order = orderRepo.getOrderById(1);
        assertNotNull(order);
        assertEquals(1, order.getOrderID());
    }

    @Test
    void getOrderById_invalidId_throwsException() {
        assertThrows(RecordNotFoundException.class, () -> orderRepo.getOrderById(9999));
    }

    @Test
    void getAllOrders_returnsNonEmptyList() throws Exception {
        List<Order> orders = orderRepo.getAllOrders();
        assertNotNull(orders);
        assertFalse(orders.isEmpty());
    }

    @Test
    void addOrder_addsSuccessfully() throws Exception {
        Order newOrder = new Order();
        newOrder.setServerID(1);
        newOrder.setOrderDate(LocalDateTime.now());
        newOrder.setSubTotal(BigDecimal.valueOf(50));
        newOrder.setTax(BigDecimal.valueOf(3));
        newOrder.setTip(BigDecimal.valueOf(10));
        newOrder.setTotal(BigDecimal.valueOf(63));

        Order created = orderRepo.addOrder(newOrder);
        assertNotNull(created);
        assertTrue(created.getOrderID() > 0);
        assertEquals(1, created.getServerID());
    }

    @Test
    void updateOrder_updatesSuccessfully() throws Exception {
        Order order = orderRepo.getOrderById(1);
        order.setTip(BigDecimal.valueOf(20));
        order.setTotal(order.getSubTotal().add(order.getTax()).add(order.getTip()));

        orderRepo.updateOrder(order);

        Order updated = orderRepo.getOrderById(1);

        assertEquals(0, updated.getTip().compareTo(BigDecimal.valueOf(20)));
    }


    @Test
    void deleteOrder_deletesAndReturnsOrder() throws Exception {
        Order deleted = orderRepo.deleteOrder(1);
        assertNotNull(deleted);
        assertEquals(1, deleted.getOrderID());

        assertThrows(RecordNotFoundException.class, () -> orderRepo.getOrderById(1));
    }
}
