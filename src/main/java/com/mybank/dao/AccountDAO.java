package com.mybank.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mybank.util.DBConnection;

public class AccountDAO {

    // Existing method
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

    // ===== New methods =====

    // AccountDetails DTO
    public static class AccountDetails {
        private int accountId;
        private String accountType;
        private double balance;

        public AccountDetails(int accountId, String accountType, double balance) {
            this.accountId = accountId;
            this.accountType = accountType;
            this.balance = balance;
        }

        public int getAccountId() { return accountId; }
        public String getAccountType() { return accountType; }
        public double getBalance() { return balance; }
    }

    // Transaction DTO
    public static class Transaction {
        private String type;
        private double amount;
        private Timestamp date;

        public Transaction(String type, double amount, Timestamp date) {
            this.type = type;
            this.amount = amount;
            this.date = date;
        }

        public String getType() { return type; }
        public double getAmount() { return amount; }
        public Timestamp getDate() { return date; }
    }

    // Get account info
    public AccountDetails getAccountDetails(int accountId) {
        AccountDetails account = null;
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT * FROM accounts WHERE account_id=?")) {
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                account = new AccountDetails(
                        rs.getInt("account_id"),
                        rs.getString("account_type"),
                        rs.getDouble("balance")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return account;
    }

    // Get transaction history
    public List<Transaction> getTransactionHistory(int accountId) {
        List<Transaction> transactions = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT transaction_type, amount, transaction_date FROM transactions WHERE account_id=? ORDER BY transaction_date DESC")) {
            ps.setInt(1, accountId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                transactions.add(new Transaction(
                        rs.getString("transaction_type"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("transaction_date")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
