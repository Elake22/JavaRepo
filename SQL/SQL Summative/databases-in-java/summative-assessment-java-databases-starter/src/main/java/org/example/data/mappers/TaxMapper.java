package org.example.data.mappers;

import org.example.model.Tax;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaxMapper {
    public static Tax map(ResultSet rs) throws SQLException {
        Tax tax = new Tax();
        tax.setTaxID(rs.getInt("TaxID"));
        tax.setTaxPercentage(rs.getBigDecimal("Rate")); // your column is Rate, but Java field is taxPercentage
        tax.setStartDate(rs.getDate("StartDate").toLocalDate());
        var endDate = rs.getDate("EndDate");
        tax.setEndDate(endDate != null ? endDate.toLocalDate() : null);
        return tax;
    }
}
