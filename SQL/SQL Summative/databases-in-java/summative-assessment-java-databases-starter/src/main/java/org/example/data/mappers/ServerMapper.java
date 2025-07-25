package org.example.data.mappers;

import org.example.model.Server;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerMapper {

    public static RowMapper<Server> serverRowMapper() {
        return (rs, rowNum) -> {
            Server server = new Server();
            server.setServerID(rs.getInt("ServerID"));
            server.setFirstName(rs.getString("FirstName"));
            server.setLastName(rs.getString("LastName"));
            server.setHireDate(rs.getDate("HireDate").toLocalDate());
            if (rs.getDate("TermDate") != null) {
                server.setTermDate(rs.getDate("TermDate").toLocalDate());
            }
            return server;
        };
    }
}
