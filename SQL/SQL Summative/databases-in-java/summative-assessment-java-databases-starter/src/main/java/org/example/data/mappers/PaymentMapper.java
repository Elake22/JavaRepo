package org.example.data.mappers;

import org.example.model.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentMapper {
    public static Payment map(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentID(rs.getInt("PaymentID"));
        payment.setPaymentTypeID(rs.getInt("PaymentTypeID"));
        payment.setOrderID(rs.getInt("OrderID"));
        payment.setAmount(rs.getBigDecimal("Amount")); // confirm this column exists
        return payment;
    }
}
