package org.example.data.mappers;

import org.example.model.Order;
import org.example.model.Server;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper {

    public static RowMapper<Order> orderRowMapper() {
        return (ResultSet rs, int rowNum) -> {
            Order order = new Order();
            order.setOrderID(rs.getInt("OrderID"));
            order.setServerID(rs.getInt("ServerID"));
            order.setOrderDate(rs.getTimestamp("OrderDate").toLocalDateTime());
            order.setSubTotal(rs.getBigDecimal("SubTotal"));
            order.setTax(rs.getBigDecimal("Tax"));
            order.setTip(rs.getBigDecimal("Tip"));
            order.setTotal(rs.getBigDecimal("Total"));
            return order;
        };
    }
    public static RowMapper<Order> orderWithServerRowMapper() {
        return (rs, rowNum) -> {
            Order order = orderRowMapper().mapRow(rs, rowNum); // use existing mapper

            Server server = new Server();
            server.setServerID(rs.getInt("ServerID"));
            server.setFirstName(rs.getString("FirstName"));
            server.setLastName(rs.getString("LastName"));
            server.setHireDate(rs.getDate("HireDate").toLocalDate());

            if (rs.getDate("TermDate") != null) {
                server.setTermDate(rs.getDate("TermDate").toLocalDate());
            }

            order.setServer(server);
            return order;
        };
    }
}
