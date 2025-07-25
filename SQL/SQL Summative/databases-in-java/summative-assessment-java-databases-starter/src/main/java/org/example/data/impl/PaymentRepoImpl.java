package org.example.data.impl;

import org.example.data.PaymentRepo;
import org.example.data.PaymentRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.mappers.PaymentMapper;
import org.example.model.Payment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentRepoImpl implements org.example.data.PaymentRepo {

    private final JdbcTemplate jdbcTemplate;

    public PaymentRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Payment> findByOrderId(int orderId) {
        final String sql = """
    SELECT p.*, pt.PaymentTypeName
    FROM Payment p
    JOIN PaymentType pt ON p.PaymentTypeID = pt.PaymentTypeID
    WHERE p.OrderID = ?
""";
        try {
            return jdbcTemplate.query(sql, PaymentMapper.paymentRowMapper(), orderId);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Unable to retrieve payments by Order ID: " + orderId, ex);
        }
    }

    @Override
    public void update(int orderId, List<Payment> payments) throws InternalErrorException {

    }
}
