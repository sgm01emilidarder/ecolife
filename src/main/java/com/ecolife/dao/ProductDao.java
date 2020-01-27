package com.ecolife.dao;

import java.sql.*;
import java.util.*;

import com.ecolife.dto.Category;
import com.ecolife.dto.Product;
import com.ecolife.dto.Type;

public class ProductDao {
    public ProductDao() {}

    public List<Product> listar() {
        String SQL_SELECT = "SELECT pro_id, pro_name, pro_price, pro_weight, pro_cover, pro_description, pro_category, pro_type " + " FROM products";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Product product = null;
        List<Product> products = new ArrayList<>();

        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("pro_id");
                String name = rs.getString("pro_name");
                double price = rs.getDouble("pro_price");
                double weight = rs.getDouble("pro_weight");
                String cover = rs.getString("pro_cover");
                String description = rs.getString("pro_description");
                Category category = Category.valueOf(rs.getString("pro_category"));
                Type type = Type.valueOf(rs.getString("pro_type"));

                product = new Product(id, name, price, weight, cover, description, category, type);
                products.add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return products;
    }

    /*
     * Recupera un client a la base de dades segons el seu ID
     *
     */
    public Product findById(Product product) {
        String SQL_SELECT_BY_ID = "SELECT pro_id, pro_name, pro_price, pro_weight, pro_cover, pro_description, pro_category, pro_type "
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
    }

    /*
     * Crea un client a la base de dades
     *
     */
    public int create(Product product) {
        String SQL_INSERT = "INSERT INTO products(pro_name, pro_price, pro_weight, pro_cover, pro_description, pro_category, pro_type) "
                + " VALUES(?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int i = 1;
            stmt.setString(i++, product.getName());
            stmt.setDouble(i++, product.getPrice());
            stmt.setDouble(i++, product.getWeight());
            stmt.setString(i++, product.getCover());
            stmt.setString(i++, product.getDescription());
            stmt.setString(i++, product.getCategory().toString());
            stmt.setString(i++, product.getType().toString());
            System.out.println(product.toString());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return rows;
    }

    /*
     * Modifica un client de la base de dades
     *
     */
    public int update(Product product) {
        String SQL_UPDATE = "UPDATE products "
                + " SET pro_name=?, pro_price=?, pro_weight=?, pro_cover=?, pro_description=?, pro_category=?, pro_type=? WHERE pro_id=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            int i = 1;
            stmt.setString(i++, product.getName());
            stmt.setDouble(i++, product.getPrice());
            stmt.setDouble(i++, product.getWeight());
            stmt.setString(i++, product.getCover());
            stmt.setString(i++, product.getDescription());
            stmt.setString(i++, product.getCategory().toString());
            stmt.setString(i++, product.getType().toString());
            stmt.setInt(i++, product.getCode());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return rows;
    }

    /*
     * Esborra un client de la base de dades
     *
     */
    public int delete(Product product) {
        String SQL_DELETE = "DELETE FROM products WHERE pro_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, product.getCode());
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