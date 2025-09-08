package com.mybank.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mybank.util.DBConnection;
import com.mybank.model.Transaction;

public class TransactionDAO {

    public double getBalance(int accountId) throws SQLException {
        String sql = "SELECT balance FROM accounts WHERE account_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
            return -1; // account not found
        }
    }

    public boolean updateBalance(int accountId, double newBalance) throws SQLException {
        String sql = "UPDATE accounts SET balance=? WHERE account_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, newBalance);
            ps.setInt(2, accountId);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean insertTransaction(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO transactions(account_id, transaction_type, amount, transaction_date) VALUES(?,?,?,NOW())";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, transaction.getAccountId());
            ps.setString(2, transaction.getType());
            ps.setDouble(3, transaction.getAmount());
            return ps.executeUpdate() > 0;
        }
    }

    public List<Transaction> getTransactionHistory(int accountId) throws SQLException {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE account_id=? ORDER BY transaction_date DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Transaction t = new Transaction();
                t.setTransactionId(rs.getInt("transaction_id"));
                t.setAccountId(rs.getInt("account_id"));
                t.setType(rs.getString("transaction_type"));
                t.setAmount(rs.getDouble("amount"));
                t.setDate(rs.getTimestamp("transaction_date"));
                list.add(t);
            }
        }
        return list;
    }
}
