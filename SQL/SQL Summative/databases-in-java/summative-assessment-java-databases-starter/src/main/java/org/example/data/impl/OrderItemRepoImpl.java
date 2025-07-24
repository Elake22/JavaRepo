package org.example.data.impl;

import org.example.data.OrderItemRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.mappers.OrderItemMapper;
import org.example.model.OrderItem;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class OrderItemRepoImpl implements OrderItemRepo {

    private final JdbcTemplate jdbcTemplate;

    public OrderItemRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<OrderItem> findByOrderId(int orderId) throws InternalErrorException {
        final String sql = "SELECT * FROM OrderItem WHERE OrderID = ?";
        try {
            return jdbcTemplate.query(sql, (rs, rowNum) -> OrderItemMapper.map(rs), orderId);
        } catch (DataAccessException ex) {
            throw new InternalErrorException("Unable to retrieve order items for order ID " + orderId, ex);
        }
    }
}
