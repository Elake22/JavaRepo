package org.example.data.mappers;

import org.example.model.PaymentType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentTypeMapper {
    public static PaymentType map(ResultSet rs) throws SQLException {
        PaymentType type = new PaymentType();
        type.setPaymentTypeID(rs.getInt("PaymentTypeID"));
        type.setPaymentTypeName(rs.getString("PaymentTypeName"));
        return type;
    }
}
