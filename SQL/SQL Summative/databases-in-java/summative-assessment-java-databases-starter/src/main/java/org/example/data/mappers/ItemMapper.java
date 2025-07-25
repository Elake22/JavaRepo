package org.example.data.mappers;

import org.example.model.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper {
    public static RowMapper<Item> itemRowMapper() {
        return (rs, rowNum) -> {
            Item item = new Item();
            item.setItemID(rs.getInt("ItemID"));
            item.setItemCategoryID(rs.getInt("ItemCategoryID"));
            item.setItemName(rs.getString("ItemName"));
            item.setItemDescription(rs.getString("ItemDescription"));
            item.setStartDate(rs.getDate("StartDate") != null ? rs.getDate("StartDate").toLocalDate() : null);
            item.setEndDate(rs.getDate("EndDate") != null ? rs.getDate("EndDate").toLocalDate() : null);
            item.setUnitPrice(rs.getBigDecimal("UnitPrice"));
            return item;
        };
    }
}
