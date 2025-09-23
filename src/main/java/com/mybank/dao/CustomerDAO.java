package com.mybank.dao;

import com.mybank.model.Customer;
import com.mybank.util.DBConnection;

import java.sql.*;

public class CustomerDAO {

    // Check if email already exists
    public boolean isEmailExist(String email) throws SQLException {
        String sql = "SELECT customer_id FROM customers WHERE email = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true if exists
            }
        }
    }

    // Create customer
    public int createCustomer(Customer c) throws SQLException {
        // First check email
        if (isEmailExist(c.getEmail())) {
            return -2; // email already exists
        }

        String sql = "INSERT INTO customers(fullname,email,password) VALUES(?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getFullname());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getPassword());

            int rows = ps.executeUpdate();
            if (rows == 0) return -1;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
            return -1;
        }
    }

     public Customer login(String email, String password) throws SQLException {
        String sql = "SELECT * FROM customers WHERE email=? AND password=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer c = new Customer();
                    c.setId(rs.getInt("customer_id"));
                    c.setFullname(rs.getString("fullname"));
                    c.setEmail(rs.getString("email"));
                    c.setPassword(rs.getString("password"));
                    return c;
                }
            }
        }
        return null; // invalid login
    }
}
