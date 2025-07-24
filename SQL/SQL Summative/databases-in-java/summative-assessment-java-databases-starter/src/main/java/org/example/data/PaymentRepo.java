package org.example.data;

import org.example.model.Payment;

import java.util.List;

public interface PaymentRepo {
    List<Payment> findByOrderId(int orderId);
}
