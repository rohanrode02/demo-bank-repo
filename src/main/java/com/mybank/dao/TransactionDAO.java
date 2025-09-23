package com.mybank.dao;

import com.mybank.model.Transaction;
import com.mybank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // Insert new transaction record
    public void saveTransaction(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO transactions (transaction_type, amount, transaction_date, account_number) VALUES (?, ?, NOW(), ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, transaction.getTransactionType());
            stmt.setDouble(2, transaction.getAmount());
            stmt.setString(3, transaction.getAccountNumber());

            stmt.executeUpdate();
        }
    }

     // Get all transactions by account number
    public List<Transaction> getTransactionsByAccount(String accountNumber) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE account_number = ? ORDER BY transaction_date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction t = new Transaction();
                t.setTransactionId(rs.getInt("transaction_id"));
                t.setTransactionType(rs.getString("transaction_type"));
                t.setAmount(rs.getDouble("amount"));
                t.setTransactionDate(rs.getTimestamp("transaction_date"));
                t.setAccountNumber(rs.getString("account_number"));
                transactions.add(t);
            }
        }
        return transactions;
    }
}
