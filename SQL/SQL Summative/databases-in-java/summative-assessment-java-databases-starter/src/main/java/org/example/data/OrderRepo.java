package org.example.data;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepo {

    Order getOrderById(int id) throws RecordNotFoundException, InternalErrorException;

    List<Order> getAllOrders() throws InternalErrorException;

    Order addOrder(Order order) throws InternalErrorException;

    void updateOrder(Order order) throws InternalErrorException;

    Order deleteOrder(int id) throws InternalErrorException;

    void deleteOrder(Order order) throws InternalErrorException;
}
