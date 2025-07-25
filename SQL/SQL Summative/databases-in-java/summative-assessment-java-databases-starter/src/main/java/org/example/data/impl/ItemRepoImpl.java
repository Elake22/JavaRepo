package org.example.data.impl;

import org.example.data.ItemRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Item;
import org.example.data.mappers.ItemMapper;
import org.example.model.ItemCategory;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepoImpl implements ItemRepo {

    private final JdbcTemplate jdbcTemplate;

    public ItemRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Item> findAll() throws InternalErrorException {
        final String sql = "SELECT * FROM item";
        try {
            return jdbcTemplate.query(sql, ItemMapper.itemRowMapper());
        } catch (DataAccessException ex) {
            throw new InternalErrorException("Unable to retrieve items", ex);
        }
    }

    @Override
    public Optional<Item> findById(int id) throws InternalErrorException {
        final String sql = "SELECT * FROM item WHERE ItemID = ?";
        try {
            Item item = jdbcTemplate.queryForObject(sql, ItemMapper.itemRowMapper(), id);
            return Optional.ofNullable(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (DataAccessException ex) {
            throw new InternalErrorException("Unable to retrieve item by ID", ex);
        }
    }

    @Override
    public Item getItemById(int id) throws RecordNotFoundException, InternalErrorException {
        return null;
    }

    @Override
    public List<Item> getAllAvailableItems(LocalDate today) throws InternalErrorException {
        return List.of();
    }

    @Override
    public List<Item> getItemsByCategory(LocalDate today, int itemCategoryID) throws InternalErrorException {
        final String sql = """
        SELECT *
        FROM Item
        WHERE ItemCategoryID = ?
          AND (StartDate IS NULL OR StartDate <= ?)
          AND (EndDate IS NULL OR EndDate >= ?)
    """;

        try {
            return jdbcTemplate.query(sql, ItemMapper.itemRowMapper(), itemCategoryID, today, today);
        } catch (DataAccessException ex) {
            throw new InternalErrorException("Unable to retrieve items by category", ex);
        }
    }


    @Override
    public List<ItemCategory> getAllItemCategories() throws InternalErrorException {
        return List.of();
    }

    @Override
    public List<Item> findByCategoryId(int categoryId) throws InternalErrorException {
        return List.of();
    }
}
