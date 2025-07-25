package org.example.data.mappers;

import org.example.model.Payment;
import org.example.model.PaymentType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper {
    public static RowMapper<Payment> paymentRowMapper() {
        return (rs, rowNum) -> {
            Payment payment = new Payment();
            payment.setPaymentID(rs.getInt("PaymentID"));
            payment.setPaymentTypeID(rs.getInt("PaymentTypeID"));
            payment.setOrderID(rs.getInt("OrderID"));
            payment.setAmount(rs.getBigDecimal("Amount"));

            PaymentType pt = new PaymentType();
            pt.setPaymentTypeID(rs.getInt("PaymentTypeID"));
            pt.setPaymentTypeName(rs.getString("PaymentTypeName"));
            payment.setPaymentType(pt);
            return payment;
        };
    }
}
