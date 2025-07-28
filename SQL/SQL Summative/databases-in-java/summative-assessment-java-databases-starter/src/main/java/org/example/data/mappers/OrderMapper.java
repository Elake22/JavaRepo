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
            Order o = new Order();
            o.setOrderID(rs.getInt("OrderID"));
            o.setServerID(rs.getInt("ServerID"));
            o.setOrderDate(rs.getTimestamp("OrderDate").toLocalDateTime());
            o.setSubTotal(rs.getBigDecimal("SubTotal"));
            o.setTax(rs.getBigDecimal("Tax"));
            o.setTip(rs.getBigDecimal("Tip"));
            o.setTotal(rs.getBigDecimal("Total"));

            Server s = new Server();
            s.setServerID(rs.getInt("ServerID"));
            s.setFirstName(rs.getString("FirstName"));
            s.setLastName(rs.getString("LastName"));
            s.setHireDate(rs.getDate("HireDate").toLocalDate());
//            if (rs.getDate("TermDate") != null)
//                s.setTermDate(rs.getDate("TermDate").toLocalDate());

            o.setServer(s);
            return o;
        };
    }

}
