package org.example.data.impl;

import org.example.data.PaymentRepo;
import org.example.data.PaymentRepo;
import org.example.data.mappers.PaymentMapper;
import org.example.model.Payment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PaymentRepoImpl implements org.example.data.PaymentRepo {

    private final JdbcTemplate jdbcTemplate;

    public PaymentRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Payment> findByOrderId(int orderId) {
        try {
            String sql = "SELECT * FROM Payment WHERE OrderID = ?";
            return jdbcTemplate.query(sql, (rs, rowNum) -> PaymentMapper.map(rs), orderId);
        } catch (DataAccessException ex) {
            throw new RuntimeException("Unable to retrieve payments by Order ID", ex);
        }
    }
}
