package org.example.data.impl;

import org.example.data.PaymentTypeRepo;
import org.example.data.mappers.PaymentTypeMapper;
import org.example.model.PaymentType;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;

public class PaymentTypeRepoImpl implements PaymentTypeRepo {

    private final JdbcTemplate jdbcTemplate;

    public PaymentTypeRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PaymentType> getAll() {
        try {
            String sql = "SELECT * FROM PaymentType";
            return jdbcTemplate.query(sql, (rs, rowNum) -> PaymentTypeMapper.map(rs));
        } catch (DataAccessException ex) {
            throw new RuntimeException("Unable to retrieve payment types", ex);
        }
    }

    @Override
    public Optional<PaymentType> findById(int id) {
        try {
            String sql = "SELECT * FROM PaymentType WHERE PaymentTypeID = ?";
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(sql, (rs, rowNum) -> PaymentTypeMapper.map(rs), id)
            );
        } catch (DataAccessException ex) {
            return Optional.empty();
        }
    }
}
