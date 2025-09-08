package com.mybank.dao;

import com.mybank.model.Customer;
import com.mybank.util.DBConnection;

import java.sql.*;

public class CustomerDAO {

    // register and return generated customerId or -1
    public int registerCustomer(Customer c) {
        String sql = "INSERT INTO customers(fullname, email, phone) VALUES(?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getFullname());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getPhone());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) return keys.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
