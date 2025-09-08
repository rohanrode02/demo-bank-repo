package com.mybank.dao;

import com.mybank.model.Account;
import com.mybank.model.Transaction;
import com.mybank.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    // Open account and return generated account_id (or -1 on failure)
    public int openAccount(Account account) {
        String sql = "INSERT INTO accounts(customer_id, account_type, balance) VALUES(?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, account.getCustomerId());
            ps.setString(2, account.getAccountType());
            ps.setDouble(3, account.getBalance());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        return keys.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Get account details
    public Account getAccountDetails(int accountId) {
        String sql = "SELECT * FROM accounts WHERE account_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(
                        rs.getInt("account_id"),
                        rs.getInt("customer_id"),
                        rs.getString("account_type"),
                        rs.getDouble("balance")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get transaction history
    public List<Transaction> getTransactionHistory(int accountId) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT transaction_id, account_id, transaction_type, amount, transaction_date FROM transactions WHERE account_id = ? ORDER BY transaction_date DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Transaction(
                        rs.getInt("transaction_id"),
                        rs.getInt("account_id"),
                        rs.getString("transaction_type"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("transaction_date")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // perform transaction (deposit/withdraw) — returns new balance or Double.NaN on error
    public double performTransaction(int accountId, String type, double amount) {
        String selectSql = "SELECT balance FROM accounts WHERE account_id = ? FOR UPDATE"; // row lock
        String updateSql = "UPDATE accounts SET balance = ? WHERE account_id = ?";
        String insertTx = "INSERT INTO transactions(account_id, transaction_type, amount) VALUES(?,?,?)";

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            try (PreparedStatement ps1 = con.prepareStatement(selectSql)) {
                ps1.setInt(1, accountId);
                try (ResultSet rs = ps1.executeQuery()) {
                    if (!rs.next()) {
                        con.rollback();
                        return Double.NaN;
                    }
                    double balance = rs.getDouble("balance");
                    if ("Withdraw".equalsIgnoreCase(type) && amount > balance) {
                        con.rollback();
                        return Double.NaN; // insufficient funds
                    }
                    double newBalance = "Deposit".equalsIgnoreCase(type) ? balance + amount : balance - amount;

                    try (PreparedStatement ps2 = con.prepareStatement(updateSql)) {
                        ps2.setDouble(1, newBalance);
                        ps2.setInt(2, accountId);
                        ps2.executeUpdate();
                    }

                    try (PreparedStatement ps3 = con.prepareStatement(insertTx)) {
                        ps3.setInt(1, accountId);
                        ps3.setString(2, type);
                        ps3.setDouble(3, amount);
                        ps3.executeUpdate();
                    }

                    con.commit();
                    return newBalance;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            try { if (con != null) con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            return Double.NaN;
        } finally {
            try { if (con != null) con.setAutoCommit(true); if (con != null) con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
