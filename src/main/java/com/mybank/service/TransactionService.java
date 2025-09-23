package com.mybank.service;

import java.util.List;

import com.mybank.dao.AccountDAO;
import com.mybank.dao.TransactionDAO;
import com.mybank.model.Account;
import com.mybank.model.Transaction;

public class TransactionService {

    private AccountDAO accountDAO;
    private TransactionDAO transactionDAO;

    public TransactionService() {
        this.accountDAO = new AccountDAO();
        this.transactionDAO = new TransactionDAO();
    }

    public void performTransaction(Transaction transaction, String enteredPin) throws Exception {
        // Get account details
        Account account = accountDAO.getAccountByNumber(transaction.getAccountNumber());

        if (account == null) {
            throw new Exception("Account not found!");
        }

        // --- Security PIN check ---
        if (!account.getSecurityPin().equals(enteredPin)) {
            throw new Exception("Invalid Security PIN!");
        }

        double newBalance = account.getBalance();

        if ("Deposit".equalsIgnoreCase(transaction.getTransactionType())) {
            newBalance += transaction.getAmount();
        } else if ("Withdraw".equalsIgnoreCase(transaction.getTransactionType())) {
            if (account.getBalance() < transaction.getAmount()) {
                throw new Exception("Insufficient Balance!");
            }
            newBalance -= transaction.getAmount();
        }

        // Save transaction
        transactionDAO.saveTransaction(transaction);

        // Update balance
        accountDAO.updateBalance(transaction.getAccountNumber(), newBalance);

    }

    // 1) Account validate करणे
    public boolean validateAccount(String accountNumber, String pin) throws Exception {
        Account account = accountDAO.getAccountByNumber(accountNumber);
        if (account != null && account.getSecurityPin().equals(pin)) {
            return true;
        }
        return false;
    }

    // 2) Account balance मिळवणे
    public double getAccountBalance(String accountNumber) throws Exception {
        Account account = accountDAO.getAccountByNumber(accountNumber);
        if (account == null) {
            throw new Exception("Account not found!");
        }
        return account.getBalance();
    }

    // 3) Transaction history मिळवणे
    public List<Transaction> getTransactions(String accountNumber) throws Exception {
        return transactionDAO.getTransactionsByAccount(accountNumber);
    }
}
