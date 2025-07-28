package org.example.data.impl;

import org.example.data.PaymentTypeRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.mappers.PaymentTypeMapper;
import org.example.model.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PaymentTypeRepoImpl implements PaymentTypeRepo {

    private final JdbcTemplate jdbcTemplate;

    public PaymentTypeRepoImpl(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PaymentType> getAll() {
        return List.of();
    }

    @Override
    public List<PaymentType> findAll() throws InternalErrorException {
        final String sql = "SELECT * FROM PaymentType";
        try {
            return jdbcTemplate.query(sql, PaymentTypeMapper.paymentTypeRowMapper());
        } catch (DataAccessException ex) {
            throw new InternalErrorException("Unable to retrieve payment types", ex);
        }
    }

    @Override
    public Optional<PaymentType> findById(int id) {
        final String sql = "SELECT * FROM PaymentType WHERE PaymentTypeID = ?";
        try {
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(sql, PaymentTypeMapper.paymentTypeRowMapper(), id)
            );
        } catch (DataAccessException ex) {
            return Optional.empty();
        }
    }
}
