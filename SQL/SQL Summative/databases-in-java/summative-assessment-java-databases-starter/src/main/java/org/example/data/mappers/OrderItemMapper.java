package org.example.data.mappers;

import org.example.model.OrderItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemMapper {
    public static OrderItem map(ResultSet rs) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItemID(rs.getInt("OrderItemID"));
        orderItem.setOrderID(rs.getInt("OrderID"));
        orderItem.setItemID(rs.getInt("ItemID"));
        orderItem.setQuantity(rs.getInt("Quantity"));
        return orderItem;
    }
}
