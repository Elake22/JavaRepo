package org.example.data.mappers;

import org.example.model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper {
    public static Item map(ResultSet rs) throws SQLException {
        Item item = new Item();
        item.setItemID(rs.getInt("ItemID"));
        item.setItemName(rs.getString("ItemName"));
        item.setUnitPrice(rs.getBigDecimal("ItemPrice"));
        item.setItemCategoryID(rs.getInt("ItemCategoryID"));
        return item;
    }
}
