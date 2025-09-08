package com.mybank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mybank.util.DBConnection;

public class AccountDAO {

    public boolean openAccount(int customerId, String accountType, double deposit) {
        String sql = "INSERT INTO accounts(customer_id, account_type, balance) VALUES(?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ps.setString(2, accountType);
            ps.setDouble(3, deposit);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
