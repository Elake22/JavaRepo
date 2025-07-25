package org.example.data.impl;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.example.data.TaxRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.mappers.TaxMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.example.model.Tax;

@Repository
public class TaxRepoImpl implements TaxRepo {

    private final JdbcTemplate jdbcTemplate;

    public TaxRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Tax create(Tax tax) throws InternalErrorException {
        String sql = "INSERT INTO tax (TaxPercentage, StartDate, EndDate) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        int rows = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBigDecimal(1, tax.getTaxPercentage());
            ps.setDate(2, Date.valueOf(tax.getStartDate()));
            if (tax.getEndDate() != null) {
                ps.setDate(3, Date.valueOf(tax.getEndDate()));
            } else {
                ps.setNull(3, Types.DATE);
            }
            return ps;
        }, keyHolder);

        if (rows > 0 && keyHolder.getKey() != null) {
            tax.setTaxID(keyHolder.getKey().intValue());
            return tax;
        } else {
            throw new InternalErrorException("Unable to create tax record");
        }
    }


    @Override
    public List<Tax> findAll() {
        return jdbcTemplate.query("SELECT * FROM Tax", (ResultSet rs, int rowNum) -> {
            Tax tax = new Tax();
            tax.setTaxID(rs.getInt("TaxID"));
            tax.setTaxPercentage(rs.getBigDecimal("TaxPercentage"));
            return tax;
        });
    }

    @Override
    public Tax getCurrentTax(LocalDate dateOf) {
        return null;
    }

    @Override
    public Optional<Tax> findById(int id) throws InternalErrorException {
        final String sql = "SELECT * FROM Tax WHERE TaxID = ?";
        try {
            Tax tax = jdbcTemplate.queryForObject(sql, TaxMapper.taxRowMapper(), id);
            return Optional.ofNullable(tax);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException ex) {
            throw new InternalErrorException("Unable to retrieve tax by ID", ex);
        }
    }


}
