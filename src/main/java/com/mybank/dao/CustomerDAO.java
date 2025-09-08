package com.mybank.dao;

import com.mybank.model.Customer;
import com.mybank.util.DBConnection;

import java.sql.*;

public class CustomerDAO {

    public int createCustomer(Customer c) throws SQLException {
        String sql = "INSERT INTO customers(fullname,email,phone) VALUES(?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getFullname());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getPhone());
            int rows = ps.executeUpdate();
            if (rows == 0) return -1;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
            return -1;
        }
    }
}
