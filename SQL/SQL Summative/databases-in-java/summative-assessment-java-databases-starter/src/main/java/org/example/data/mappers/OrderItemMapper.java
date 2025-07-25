package org.example.data.mappers;

import org.example.model.Item;
import org.example.model.OrderItem;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItemMapper {
    public static RowMapper<OrderItem> orderItemRowMapper() {
        return (rs, rowNum) -> {
            OrderItem oi = new OrderItem();
            oi.setOrderItemID(rs.getInt("OrderItemID"));
            oi.setOrderID(rs.getInt("OrderID"));
            oi.setItemID(rs.getInt("ItemID"));
            oi.setQuantity(rs.getInt("Quantity"));
            oi.setPrice(rs.getBigDecimal("UnitPrice")); // renamed to match SQL alias

            // Set Item reference
            Item item = new Item();
            item.setItemID(rs.getInt("ItemID"));
            item.setUnitPrice(rs.getBigDecimal("UnitPrice"));
            item.setItemName(rs.getString("ItemName"));

            oi.setItem(item);
            return oi;
        };
    }
}

