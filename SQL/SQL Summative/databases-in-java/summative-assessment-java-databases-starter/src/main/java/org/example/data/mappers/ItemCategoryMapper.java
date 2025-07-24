package org.example.data.mappers;

import org.example.model.ItemCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemCategoryMapper {
    public static ItemCategory map(ResultSet rs) throws SQLException {
        ItemCategory category = new ItemCategory();
        category.setItemCategoryID(rs.getInt("ItemCategoryID"));
        category.setItemCategoryName(rs.getString("ItemCategoryName"));
        return category;
    }
}
