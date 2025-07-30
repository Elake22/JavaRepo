package org.example.data.impl;

import org.example.data.OrderRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.mappers.*;
import org.example.model.Item;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.data.mappers.OrderMapper.orderRowMapper;

@Repository
public class OrderRepoImpl implements OrderRepo {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepoImpl(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Order getOrderById(int id) throws RecordNotFoundException, InternalErrorException {
        try {
            CallableStatementCallback<Order> callback = cs -> {
                cs.setInt(1, id);

                boolean hasResults = cs.execute();
                Order order = null;

                // 1. First result set: Order + Server
                if (hasResults) {
                    try (ResultSet rs = cs.getResultSet()) {
                        if (rs.next()) {
                            order = orderRowMapper().mapRow(rs, 1);
                            order.setServer(ServerMapper.serverRowMapper().mapRow(rs, 1));
                        }
                    }
                }

                // 2. Second result set: OrderItems + Items + ItemCategories
                List<OrderItem> items = new ArrayList<>();
                hasResults = cs.getMoreResults();
                if (hasResults) {
                    try (ResultSet rs = cs.getResultSet()) {
                        while (rs.next()) {
                            OrderItem oi = OrderItemMapper.orderItemRowMapper().mapRow(rs, 1);
                            Item i = ItemMapper.itemRowMapper().mapRow(rs, 1);
                            i.setItemCategory(ItemCategoryMapper.itemCategoryRowMapper().mapRow(rs, 1));
                            oi.setItem(i);
                            items.add(oi);
                        }
                    }
                }

                // 3. Third result set: Payments + PaymentTypes
                List<Payment> payments = new ArrayList<>();
                hasResults = cs.getMoreResults();
                if (hasResults) {
                    try (ResultSet rs = cs.getResultSet()) {
                        while (rs.next()) {
                            Payment p = PaymentMapper.paymentRowMapper().mapRow(rs, 1);
                            p.setPaymentType(PaymentTypeMapper.paymentTypeRowMapper().mapRow(rs, 1));
                            payments.add(p);
                        }
                    }
                }

                if (order != null) {
                    order.setItems(items);
                    order.setPayments(payments);
                }

                return order;
            };
            Order result = jdbcTemplate.execute("{CALL get_order_with_details(?)}", callback);

            if (result == null) {
                throw new RecordNotFoundException("Order with ID " + id + " not found.");
            }

            return result;


        } catch (RecordNotFoundException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalErrorException("Failed to retrieve order details", ex);
        }
    }



    @Override
    public List<Order> getAllOrders() throws InternalErrorException {
        try {
            String sql = """
            SELECT o.*, s.ServerID, s.FirstName, s.LastName, s.HireDate, s.TermDate
            FROM `Order` o
            JOIN Server s ON o.ServerID = s.ServerID
            ORDER BY o.OrderID
        """;
            return jdbcTemplate.query(sql, OrderMapper.orderWithServerRowMapper());
        } catch (Exception ex) {
            throw new InternalErrorException("Error retrieving all orders", ex);
        }
    }


    @Override
    public Order addOrder(Order order) throws InternalErrorException {
        try {
            String sql = """
                INSERT INTO `Order` (ServerID, OrderDate, SubTotal, Tax, Tip, Total)
                VALUES (?, ?, ?, ?, ?, ?)
            """;

            jdbcTemplate.update(sql,
                    order.getServerID(),
                    Timestamp.valueOf(order.getOrderDate()),
                    order.getSubTotal(),
                    order.getTax(),
                    order.getTip(),
                    order.getTotal()
            );

            // retrieve and return the last inserted order
            String getLastId = "SELECT o.*, s.FirstName, s.LastName, s.HireDate\n" +
                    "FROM `Order` o\n" +
                    "JOIN Server s ON o.ServerID = s.ServerID\n" +
                    "ORDER BY o.OrderID DESC\n" +
                    "LIMIT 1\n";
            return jdbcTemplate.queryForObject(getLastId, OrderMapper.orderWithServerRowMapper());


        } catch (Exception ex) {
            throw new InternalErrorException("Error adding order", ex);
        }
    }

    @Override
    public void updateOrder(Order order) throws InternalErrorException {
        try {
            String sql = """
                UPDATE `Order`
                SET ServerID = ?, OrderDate = ?, SubTotal = ?, Tax = ?, Tip = ?, Total = ?
                WHERE OrderID = ?
            """;

            jdbcTemplate.update(sql,
                    order.getServerID(),
                    Timestamp.valueOf(order.getOrderDate()),
                    order.getSubTotal(),
                    order.getTax(),
                    order.getTip(),
                    order.getTotal(),
                    order.getOrderID()
            );
        } catch (Exception ex) {
            throw new InternalErrorException("Error updating order", ex);
        }
    }

    @Override
    @Transactional
    public Order deleteOrder(int id) throws InternalErrorException {
        try {
            Order toDelete = getOrderById(id);

            // Step 1: Delete child records
            jdbcTemplate.update("DELETE FROM Payment WHERE OrderID = ?", id);
            jdbcTemplate.update("DELETE FROM OrderItem WHERE OrderID = ?", id);

            // Step 2: Delete the parent order record
            jdbcTemplate.update("DELETE FROM `Order` WHERE OrderID = ?", id);

            return toDelete;

        } catch (RecordNotFoundException ex) {
            throw new InternalErrorException("Order not found to delete", ex);
        } catch (Exception ex) {
            throw new InternalErrorException("Error deleting order", ex);
        }
    }


    @Override
    public void deleteOrder(Order order) throws InternalErrorException {

    }
}
