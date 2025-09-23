package com.mybank.service;

import com.mybank.dao.AccountDAO;
import com.mybank.model.Account;

import java.util.Random;

public class AccountService {
    private AccountDAO dao = new AccountDAO();

    public String openAccount(Account account) {
        if (dao.accountExists(account.getEmail(), account.getSecurityPin())) {
            return "duplicate";
        }

        // Account number generate and set
        String accNumber = "ACC" + (100000 + new Random().nextInt(900000));
        account.setAccountNumber(accNumber);

        boolean saved = dao.saveAccount(account);
        return saved ? "success" : "failed";
    }
}
