package org.example.data;

import org.example.data.exceptions.InternalErrorException;
import org.example.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaymentRepo {
    List<Payment> findByOrderId(int orderId);

    void update(int orderId, List<Payment> payments) throws InternalErrorException;
}
