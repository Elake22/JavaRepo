package org.example.data;

import org.example.model.OrderItem;
import org.example.data.exceptions.InternalErrorException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepo {
    List<OrderItem> findByOrderId(int orderId) throws InternalErrorException;

    void update(int orderId, List<OrderItem> items) throws InternalErrorException;
}
