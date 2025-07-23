package org.example.data.impl;

import java.sql.*;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.example.model.Tax;

@Repository
public class TaxRepoImpl {

    private final JdbcTemplate jdbcTemplate;

    public TaxRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Tax> findAll() {
        return jdbcTemplate.query("SELECT * FROM Tax", (ResultSet rs, int rowNum) -> {
            Tax tax = new Tax();
            tax.setTaxID(rs.getInt("TaxID"));
            tax.setTaxPercentage(rs.getBigDecimal("TaxPercentage"));
            return tax;
        });
    }
}
