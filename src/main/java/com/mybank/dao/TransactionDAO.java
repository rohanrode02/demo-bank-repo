package com.mybank.dao;

import com.mybank.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {

    public double getBalance(Connection con, int accountId) throws SQLException {
        String sql = "SELECT balance FROM accounts WHERE account_id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getDouble("balance");
                return -1;
            }
        }
    }

    public boolean updateBalance(Connection con, int accountId, double newBalance) throws SQLException {
        String sql = "UPDATE accounts SET balance=? WHERE account_id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, newBalance);
            ps.setInt(2, accountId);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean insertTransaction(Connection con, int accountId, String type, double amount) throws SQLException {
        String sql = "INSERT INTO transactions(account_id, transaction_type, amount) VALUES(?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ps.setString(2, type);
            ps.setDouble(3, amount);
            return ps.executeUpdate() > 0;
        }
    }
}
