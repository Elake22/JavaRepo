package org.example.data;

import org.example.model.PaymentType;

import java.util.List;
import java.util.Optional;

public interface PaymentTypeRepo {
    List<PaymentType> getAll();
    Optional<PaymentType> findById(int id);
}
