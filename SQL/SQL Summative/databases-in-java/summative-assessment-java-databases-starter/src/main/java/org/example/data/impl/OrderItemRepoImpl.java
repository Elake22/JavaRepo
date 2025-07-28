package org.example.data.impl;

import org.example.data.OrderItemRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.mappers.OrderItemMapper;
import org.example.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemRepoImpl implements OrderItemRepo {

    private final JdbcTemplate jdbcTemplate;

    public OrderItemRepoImpl(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<OrderItem> findByOrderId(int orderId) throws InternalErrorException {
        final String sql = """
        SELECT
          oi.OrderItemID,
          oi.OrderID,
          oi.ItemID,
          oi.Quantity,
          i.UnitPrice,
          i.ItemName
        FROM OrderItem oi
        JOIN Item i ON oi.ItemID = i.ItemID
        WHERE oi.OrderID = ?
    """;

        try {
            List<OrderItem> items = jdbcTemplate.query(sql, OrderItemMapper.orderItemRowMapper(), orderId);
            System.out.println("Retrieved order items for order ID " + orderId + ": " + items);
            return items;
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new InternalErrorException("Unable to retrieve order items for order ID " + orderId, ex);
        }
    }


    @Override
    public void update(int orderId, List<OrderItem> items) throws InternalErrorException {

    }
}
