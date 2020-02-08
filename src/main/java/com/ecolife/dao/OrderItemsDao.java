package com.ecolife.dao;

import java.sql.*;
import java.util.*;

import com.ecolife.dto.Order;
import com.ecolife.dto.OrderItems;
import com.ecolife.dto.Product;
import com.ecolife.dao.ProductDao;

public class OrderItemsDao {
    public OrderItemsDao() {}

    public List<OrderItems> listar() {
        String SQL_SELECT = "SELECT oit_ord_id, oit_pro_id, oit_unit_price, oit_quantity " + " FROM order_items";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        OrderItems item = null;
        List<OrderItems> items = new ArrayList<>();

        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Order orderItemId = new Order(rs.getInt("oit_ord_id"));
                Product productId = new Product(rs.getInt("oit_pro_id"));
                double unitPrice = rs.getDouble("oit_unit_price");
                double quantity = rs.getDouble("oit_quantity");

                item = new OrderItems(orderItemId, productId, unitPrice, quantity);
                items.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return items;
    }

    public List<OrderItems> listByOrder(Order order) {
        String SQL_SELECT = "SELECT oit_ord_id, oit_pro_id, oit_unit_price, oit_quantity " +
                            " FROM order_items WHERE oit_ord_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        OrderItems item;
        List<OrderItems> items = new ArrayList<>();

        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            stmt.setInt(1, order.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Order orderItemId = new Order(rs.getInt("oit_ord_id"));
                Product productId = new Product(rs.getInt("oit_pro_id"));
                Product product = new ProductDao().findById(productId);
                double unitPrice = rs.getDouble("oit_unit_price");
                double quantity = rs.getDouble("oit_quantity");

                item = new OrderItems(orderItemId, product, unitPrice, quantity);
                items.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return items;
    }

    public OrderItems findById(OrderItems item) {
        String SQL_SELECT_BY_ID = "SELECT oit_unit_price, oit_quantity "
                + " FROM order_items WHERE oit_ord_id = ? AND oit_pro_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);

            stmt.setInt(1, item.getOrderId().getId());
            stmt.setInt(2, item.getProductCode().getCode());
            rs = stmt.executeQuery();
            rs.absolute(1);

            double unitPrice = rs.getDouble("oit_unit_price");
            double quantity = rs.getDouble("oit_quantity");

            item.setUnitPrice(unitPrice);
            item.setQuantity(quantity);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return item;
    }

    public int create(OrderItems item) {
        String SQL_INSERT = "INSERT INTO order_items(oit_ord_id, oit_pro_id, oit_unit_price, oit_quantity) "
                + " VALUES(?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int i = 1;
            stmt.setInt(i++, item.getOrderId().getId());
            stmt.setInt(i++, item.getProductCode().getCode());
            stmt.setDouble(i++, item.getUnitPrice());
            stmt.setDouble(i++, item.getQuantity());
            System.out.println(item.toString());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return rows;
    }

    public int update(OrderItems item) {
        String SQL_UPDATE = "UPDATE order_items "
                + " SET oit_unit_price=?, oit_quantity=? WHERE oit_ord_id=? AND oit_pro_id=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            int i = 1;
            stmt.setDouble(i++, item.getUnitPrice());
            stmt.setDouble(i++, item.getQuantity());
            stmt.setInt(i++, item.getOrderId().getId());
            stmt.setInt(i++, item.getProductCode().getCode());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return rows;
    }

    public int delete(OrderItems item) {
        String SQL_DELETE = "DELETE FROM order_items WHERE oit_ord_id=? AND oit_pro_id=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, item.getOrderId().getId());
            stmt.setInt(2, item.getProductCode().getCode());
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
