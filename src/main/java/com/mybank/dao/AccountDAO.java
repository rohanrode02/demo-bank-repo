package com.mybank.dao;

import com.mybank.model.Account;
import com.mybank.model.Transaction;
import com.mybank.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {

    public int openAccount(Account account) throws SQLException {
        String sql = "INSERT INTO accounts(customer_id, account_type, balance) VALUES(?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, account.getCustomerId());
            ps.setString(2, account.getAccountType());
            ps.setDouble(3, account.getBalance());

            int rows = ps.executeUpdate();
            if (rows == 0) return -1;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
            return -1;
        }
    }

    public Account getAccountById(int accountId) throws SQLException {
        String sql = "SELECT account_id, customer_id, account_type, balance FROM accounts WHERE account_id=?";
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
        }
        return null;
    }

    public List<Transaction> getTransactionHistory(int accountId) throws SQLException {
    List<Transaction> list = new ArrayList<>();
    String sql = "SELECT transaction_id, account_id, transaction_type, amount, transaction_date " +
                 "FROM transactions WHERE account_id=? ORDER BY transaction_date DESC";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setInt(1, accountId);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Transaction(
                    rs.getInt("transaction_id"),  // बदलले id → transaction_id
                    rs.getInt("account_id"),
                    rs.getString("transaction_type"),
                    rs.getDouble("amount"),
                    rs.getTimestamp("transaction_date")
                ));
            }
        }
    }
    return list;
}

}
