package org.example.data.mappers;

import org.example.model.ItemCategory;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemCategoryMapper {
    public static RowMapper<ItemCategory> itemCategoryRowMapper() {
        return (rs, rowNum) -> {
            ItemCategory category = new ItemCategory();
            category.setItemCategoryID(rs.getInt("ItemCategoryID"));
            category.setItemCategoryName(rs.getString("ItemCategoryName"));
            return category;
        };
    }
}
