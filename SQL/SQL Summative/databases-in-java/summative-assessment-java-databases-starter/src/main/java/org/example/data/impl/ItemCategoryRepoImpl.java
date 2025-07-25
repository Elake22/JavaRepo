package org.example.data.impl;

import org.example.data.ItemCategoryRepo;
import org.example.data.mappers.ItemCategoryMapper;
import org.example.model.ItemCategory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemCategoryRepoImpl implements ItemCategoryRepo {

    private final JdbcTemplate jdbcTemplate;

    public ItemCategoryRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ItemCategory> findAll() {
        String sql = "SELECT * FROM ItemCategory";
        return jdbcTemplate.query(sql, ItemCategoryMapper.itemCategoryRowMapper());
    }

    @Override
    public Optional<ItemCategory> findById(int id) {
        try {
            String sql = "SELECT * FROM ItemCategory WHERE ItemCategoryID = ?";
            ItemCategory itemCategory = jdbcTemplate.queryForObject(sql, ItemCategoryMapper.itemCategoryRowMapper(), id);
            return Optional.ofNullable(itemCategory);
        } catch (DataAccessException ex) {
            return Optional.empty();
        }
    }
}
