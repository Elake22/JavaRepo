package org.example.data;

import org.example.model.OrderItem;
import org.example.data.exceptions.InternalErrorException;

import java.util.List;

public interface OrderItemRepo {
    List<OrderItem> findByOrderId(int orderId) throws InternalErrorException;
}
