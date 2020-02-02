package com.ecolife.dao;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import com.ecolife.dto.Order;
import com.ecolife.dto.User;

public class OrderDao {
    public OrderDao() {}

    public List<Order> listar() {
        String SQL_SELECT = "SELECT ord_id, ord_cus_id, ord_date, ord_total " + " FROM orders";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Order order = null;
        List<Order> orders = new ArrayList<>();

        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ord_id");
                User customerId = new User(rs.getInt("ord_cus_id"));
                LocalDate date = LocalDate.parse(rs.getString("ord_date"));
                double total = rs.getDouble("ord_total");

                order = new Order(id, customerId, date, total);
                orders.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return orders;
    }

    public List<Order> listByCustomer(User user) {
        String SQL_SELECT = "SELECT ord_id, ord_cus_id, ord_date, ord_total " +
                            " FROM orders WHERE ord_cus_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Order order;
        List<Order> orders = new ArrayList<>();

        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, user.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ord_id");
                User customerId = new User(rs.getInt("ord_cus_id"));
                LocalDate date = LocalDate.parse(rs.getString("ord_date"));
                double total = rs.getDouble("ord_total");

                order = new Order(id, customerId, date, total);
                orders.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return orders;
    }

    public Order findById(Order order) {
        String SQL_SELECT_BY_ID = "SELECT ord_cus_id, ord_date, ord_total "
                + " FROM orders WHERE ord_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);

            stmt.setInt(1, order.getId());
            rs = stmt.executeQuery();
            rs.absolute(1);

            User customerId = new User(rs.getInt("ord_cus_id"));
            LocalDate date = LocalDate.parse(rs.getString("ord_date"));
            double total = rs.getDouble("ord_total");

            order.setuserId(customerId);
            order.setOrderDate(date);
            order.setOrderTotal(total);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return order;
    }

    public int create(Order order) {
        String SQL_INSERT = "INSERT INTO orders(ord_cus_id, ord_date, ord_total) "
                + " VALUES(?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int i = 1;
            stmt.setInt(i++, order.getUserId().getId());
            Date date = Date.valueOf(order.getOrderDate());
            stmt.setDate(i++, date);
            stmt.setDouble(i++, order.getOrderTotal());
            System.out.println(order.toString());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return rows;
    }

    public int update(Order order) {
        String SQL_UPDATE = "UPDATE orders "
                + " SET ord_cus_id=?, ord_date=?, ord_total=? WHERE ord_id=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            int i = 1;
            stmt.setInt(i++, order.getUserId().getId());
            Date date = Date.valueOf(order.getOrderDate());
            stmt.setDate(i++, date);
            stmt.setDouble(i++, order.getOrderTotal());
            stmt.setInt(i++, order.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return rows;
    }

    public int delete(Order order) {
        String SQL_DELETE = "DELETE FROM orders WHERE ord_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, order.getId());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return rows;
    }
}
