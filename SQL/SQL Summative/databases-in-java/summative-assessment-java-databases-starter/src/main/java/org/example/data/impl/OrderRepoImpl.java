package org.example.data.impl;

import org.example.data.OrderRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.mappers.OrderMapper;
import org.example.model.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class OrderRepoImpl implements OrderRepo {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order getOrderById(int id) throws RecordNotFoundException, InternalErrorException {
        try {
            String sql = "SELECT * FROM `Order` WHERE OrderID = ?";
            return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> OrderMapper.map(rs), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException("Order with ID " + id + " not found.");
        } catch (Exception ex) {
            throw new InternalErrorException("Error retrieving order by ID", ex);
        }
    }

    @Override
    public List<Order> getAllOrders() throws InternalErrorException, RecordNotFoundException {
        try {
            String sql = "SELECT * FROM `Order`";
            return jdbcTemplate.query(sql, (rs, rowNum) -> OrderMapper.map(rs));
        } catch (Exception ex) {
            throw new InternalErrorException("Error retrieving all orders", ex);
        }
    }

    @Override
    public Order addOrder(Order order) throws InternalErrorException {
        try {
            String sql = """
                INSERT INTO `Order` (ServerID, OrderDate, SubTotal, Tax, Tip, Total)
                VALUES (?, ?, ?, ?, ?, ?)
            """;

            jdbcTemplate.update(sql,
                    order.getServerID(),
                    Timestamp.valueOf(order.getOrderDate()),
                    order.getSubTotal(),
                    order.getTax(),
                    order.getTip(),
                    order.getTotal()
            );

            // retrieve and return the last inserted order
            String getLastId = "SELECT * FROM `Order` ORDER BY OrderID DESC LIMIT 1";
            return jdbcTemplate.queryForObject(getLastId, (rs, rowNum) -> OrderMapper.map(rs));

        } catch (Exception ex) {
            throw new InternalErrorException("Error adding order", ex);
        }
    }

    @Override
    public void updateOrder(Order order) throws InternalErrorException {
        try {
            String sql = """
                UPDATE `Order`
                SET ServerID = ?, OrderDate = ?, SubTotal = ?, Tax = ?, Tip = ?, Total = ?
                WHERE OrderID = ?
            """;

            jdbcTemplate.update(sql,
                    order.getServerID(),
                    Timestamp.valueOf(order.getOrderDate()),
                    order.getSubTotal(),
                    order.getTax(),
                    order.getTip(),
                    order.getTotal(),
                    order.getOrderID()
            );
        } catch (Exception ex) {
            throw new InternalErrorException("Error updating order", ex);
        }
    }

    @Override
    public Order deleteOrder(int id) throws InternalErrorException {
        try {
            Order toDelete = getOrderById(id); // may throw RecordNotFoundException

            String sql = "DELETE FROM `Order` WHERE OrderID = ?";
            jdbcTemplate.update(sql, id);

            return toDelete;
        } catch (RecordNotFoundException ex) {
            // Optional: return null or rethrow — depending on your intended behavior
            throw new InternalErrorException("Order not found to delete", ex);
        } catch (Exception ex) {
            throw new InternalErrorException("Error deleting order", ex);
        }
    }
}
