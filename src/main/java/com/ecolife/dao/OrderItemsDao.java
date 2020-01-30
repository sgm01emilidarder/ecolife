package com.ecolife.dao;

import java.sql.*;
import java.util.*;

import com.ecolife.dto.Order;
import com.ecolife.dto.OrderItems;
import com.ecolife.dto.Product;

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
                int quantity = rs.getInt("oit_quantity");

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

 /*   public OrderItems findById(OrderItems item) {
        String SQL_SELECT_BY_ID = "SELECT oit_ord_id, oit_pro_id, oit_unit_price, oit_quantity "
                + " FROM products WHERE pro_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);

            stmt.setInt(1, product.getCode());
            rs = stmt.executeQuery();
            rs.absolute(1);

            String name = rs.getString("pro_name");
            double price = rs.getDouble("pro_price");
            double weight = rs.getDouble("pro_weight");
            String cover = rs.getString("pro_cover");
            String description = rs.getString("pro_description");
            Category category = Category.valueOf(rs.getString("pro_category"));
            Type type = Type.valueOf(rs.getString("pro_type"));

            product.setName(name);
            product.setPrice(price);
            product.setWeight(weight);
            product.setCover(cover);
            product.setDescription(description);
            product.setCategory(category);
            product.setType(type);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return product;
    }*/

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
            stmt.setInt(i++, item.getQuantity());
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
            stmt.setInt(i++, item.getQuantity());
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
