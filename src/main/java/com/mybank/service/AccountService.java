package com.mybank.service;

import com.mybank.dao.AccountDAO;
import com.mybank.model.Account;
import com.mybank.model.Transaction;

import java.util.List;

public class AccountService {
    private AccountDAO accountDAO = new AccountDAO();

    public int openAccount(int customerId, String accountType, double deposit) throws Exception {
        Account acct = new Account(0, customerId, accountType, deposit);
        return accountDAO.openAccount(acct); // returns new account id or -1
    }

    public Account getAccount(int accountId) throws Exception {
        return accountDAO.getAccountById(accountId);
    }

    public List<Transaction> getTransactionHistory(int accountId) throws Exception {
        return accountDAO.getTransactionHistory(accountId);
    }
}
