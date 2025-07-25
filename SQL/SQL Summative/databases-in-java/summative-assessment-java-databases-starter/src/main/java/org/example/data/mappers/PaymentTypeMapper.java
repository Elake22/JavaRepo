package org.example.data.mappers;

import org.example.model.PaymentType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentTypeMapper {
    public static RowMapper<PaymentType> paymentTypeRowMapper() {
        return (rs, rowNum) -> {

            PaymentType type = new PaymentType();
            type.setPaymentTypeID(rs.getInt("PaymentTypeID"));
            type.setPaymentTypeName(rs.getString("PaymentTypeName"));
            return type;
        };
    }
}
