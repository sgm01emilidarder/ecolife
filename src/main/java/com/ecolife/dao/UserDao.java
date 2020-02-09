package com.ecolife.dao;

import java.sql.*;
import java.util.*;

import com.ecolife.dto.User;

public class UserDao {

    public UserDao() {}

    public List<User> listar() {
        String SQL_SELECT = "SELECT cus_id, cus_dni, cus_name, cus_surname, cus_username, cus_password, cus_phone, cus_email " + " FROM customers";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user;
        List<User> users = new ArrayList<>();

        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("cus_id");
                String dni = rs.getString("cus_dni");
                String name = rs.getString("cus_name");
                String surname = rs.getString("cus_surname");
                String  username = rs.getString("cus_username");
                String password = rs.getString("cus_password");
                int phone = rs.getInt("cus_phone");
                String email = rs.getString("cus_email");

                user = new User(id, dni, name, surname, username, password, phone, email);
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return users;
    }

    public User findById(User user) {
        String SQL_SELECT_BY_ID = "SELECT cus_id, cus_dni, cus_name, cus_surname, cus_username, cus_password, cus_phone, cus_email "
                + " FROM customers WHERE cus_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);

            stmt.setInt(1, user.getId());
            rs = stmt.executeQuery();
            rs.absolute(1);

            String dni = rs.getString("cus_dni");
            String name = rs.getString("cus_name");
            String surname = rs.getString("cus_surname");
            String  username = rs.getString("cus_username");
            String password = rs.getString("cus_password");
            int phone = rs.getInt("cus_phone");
            String email = rs.getString("cus_email");

            user.setDni(dni);
            user.setName(name);
            user.setSurname(surname);
            user.setUsername(username);
            user.setPassword(password);
            user.setPhone(phone);
            user.setEmail(email);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return user;
    }

    public User findByUserPass(User user) {
        String SQL_SELECT_BY_USER_AND_PASS = "SELECT cus_id, cus_dni, cus_name, cus_surname, cus_username, cus_password, cus_phone, cus_email "
                + " FROM customers WHERE cus_username = ? AND cus_password = SHA2(?,256)";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_USER_AND_PASS);

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            rs = stmt.executeQuery();
            rs.absolute(1);

            int id = rs.getInt("cus_id");
            String dni = rs.getString("cus_dni");
            String name = rs.getString("cus_name");
            String surname = rs.getString("cus_surname");
            String  username = rs.getString("cus_username");
            String password = rs.getString("cus_password");
            int phone = rs.getInt("cus_phone");
            String email = rs.getString("cus_email");

            user.setId(id);
            user.setDni(dni);
            user.setName(name);
            user.setSurname(surname);
            user.setUsername(username);
            user.setPassword(password);
            user.setPhone(phone);
            user.setEmail(email);


        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(rs);
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return user;
    }

    public int create(User user) {
        String SQL_INSERT = "INSERT INTO customers(cus_dni, cus_name, cus_surname, cus_username, cus_password, cus_phone, cus_email) "
                + " VALUES(?, ?, ?, ?, SHA2(?,256), ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            int i = 1;
            stmt.setString(i++, user.getDni());
            stmt.setString(i++, user.getName());
            stmt.setString(i++, user.getSurname());
            stmt.setString(i++, user.getUsername());
            stmt.setString(i++, user.getPassword());
            stmt.setInt(i++, user.getPhone());
            stmt.setString(i++, user.getEmail());
            System.out.println(user.toString());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return rows;
    }

    public int update(User user) {
        String SQL_UPDATE = "UPDATE customers "
                + " SET cus_dni=?, cus_name=?, cus_surname=?, cus_username=?, cus_password=SHA2(?,256), cus_phone=?, cus_email=? WHERE cus_id=?";
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            int i = 1;
            stmt.setString(i++, user.getDni());
            stmt.setString(i++, user.getName());
            stmt.setString(i++, user.getSurname());
            stmt.setString(i++, user.getUsername());
            stmt.setString(i++, user.getPassword());
            stmt.setInt(i++, user.getPhone());
            stmt.setString(i++, user.getEmail());
            stmt.setInt(i++, user.getId());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            DBConnection.close(stmt);
            DBConnection.close(conn);
        }
        return rows;
    }

    public int delete(User user) {
        String SQL_DELETE = "DELETE FROM customers WHERE cus_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = DBConnection.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, user.getId());
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
