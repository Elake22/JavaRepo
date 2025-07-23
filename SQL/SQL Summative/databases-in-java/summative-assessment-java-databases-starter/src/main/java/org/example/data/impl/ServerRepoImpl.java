package org.example.data.impl;

import org.example.data.ServerRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.mappers.ServerMapper;
import org.example.model.Server;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ServerRepoImpl implements ServerRepo {

    private final JdbcTemplate jdbcTemplate;

    public ServerRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Server getServerById(int id) throws InternalErrorException, RecordNotFoundException {
        try {
            String sql = "SELECT ServerID, FirstName, LastName, HireDate, TermDate FROM Server WHERE ServerID = ?";
            return jdbcTemplate.queryForObject(sql, (ResultSet rs, int rowNum) -> ServerMapper.map(rs), id);
        } catch (org.springframework.dao.EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException("Server with ID " + id + " not found.");
        } catch (Exception ex) {
            throw new InternalErrorException("Error retrieving server by ID", ex);
        }
    }

    @Override
    public List<Server> getAllAvailableServers(LocalDate date) throws InternalErrorException {
        try {
            String sql = "SELECT ServerID, FirstName, LastName, HireDate, TermDate FROM Server WHERE HireDate <= ? AND (TermDate IS NULL OR TermDate >= ?)";
            return jdbcTemplate.query(sql, (rs, rowNum) -> ServerMapper.map(rs), date, date);
        } catch (Exception ex) {
            throw new InternalErrorException("Error retrieving available servers", ex);
        }
    }
}
