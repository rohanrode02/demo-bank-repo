package com.mybank.service;

import com.mybank.dao.TransactionDAO;
import com.mybank.util.DBConnection;

import java.sql.Connection;

public class TransactionService {

    private TransactionDAO transactionDAO = new TransactionDAO();

    /**
     * Performs deposit/withdraw as a single DB transaction.
     * returns new balance
     */
    public double performTransaction(int accountId, String type, double amount) throws Exception {
        try (Connection con = DBConnection.getConnection()) {
            try {
                con.setAutoCommit(false);

                double balance = transactionDAO.getBalance(con, accountId);
                if (balance < 0) throw new Exception("Account not found");

                if ("Withdraw".equalsIgnoreCase(type) && amount > balance) {
                    throw new Exception("Insufficient funds");
                }

                double newBalance = "Deposit".equalsIgnoreCase(type) ? (balance + amount) : (balance - amount);

                boolean upd = transactionDAO.updateBalance(con, accountId, newBalance);
                if (!upd) throw new Exception("Failed to update balance");

                boolean ins = transactionDAO.insertTransaction(con, accountId, type, amount);
                if (!ins) throw new Exception("Failed to insert transaction");

                con.commit();
                return newBalance;
            } catch (Exception ex) {
                con.rollback();
                throw ex;
            } finally {
                con.setAutoCommit(true);
            }
        }
    }
}
