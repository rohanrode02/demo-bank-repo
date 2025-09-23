package com.mybank.dao;

import com.mybank.model.Account;
import com.mybank.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    public boolean saveAccount(Account account) {
        String sql = "INSERT INTO accounts(account_number, fullname, email, security_pin, balance) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, account.getAccountNumber());
            ps.setString(2, account.getFullname());
            ps.setString(3, account.getEmail());
            ps.setString(4, account.getSecurityPin());
            ps.setDouble(5, account.getBalance());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean accountExists(String email, String pin) {
    String sql = "SELECT COUNT(*) FROM accounts WHERE email = ? AND security_pin = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, email);
        ps.setString(2, pin);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


    public Account getAccountByNumber(String accountNumber) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setAccountId(rs.getInt("account_id"));
                acc.setAccountNumber(rs.getString("account_number"));
                acc.setFullname(rs.getString("fullname"));
                acc.setEmail(rs.getString("email"));
                acc.setSecurityPin(rs.getString("security_pin"));
                acc.setBalance(rs.getDouble("balance"));
                return acc;
            }
        }
        return null;
    }

    // Update balance
    public void updateBalance(String accountNumber, double newBalance) throws SQLException {
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newBalance);
            stmt.setString(2, accountNumber);
            stmt.executeUpdate();
        }
    }

    public boolean validateAccount(String accountNumber, String securityPin) {
    String sql = "SELECT COUNT(*) FROM accounts WHERE account_number = ? AND security_pin = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, accountNumber);
        ps.setString(2, securityPin);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1) > 0;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}


    
}
