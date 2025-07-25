package org.example.data;

import org.example.data.exceptions.InternalErrorException;
import org.example.model.PaymentType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PaymentTypeRepo {
    List<PaymentType> getAll();

    List<PaymentType> findAll() throws InternalErrorException;
    Optional<PaymentType> findById(int id);
}
