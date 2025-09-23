package com.mybank.model;

public class Account {
    private int accountId;
    private String accountNumber;
    private String fullname;
    private String email;
    private String securityPin;
    private double balance;

    public Account() {}

    public Account(String accountNumber, String fullname, String email, String securityPin, double balance) {
        this.accountNumber = accountNumber;
        this.fullname = fullname;
        this.email = email;
        this.securityPin = securityPin;
        this.balance = balance;
    }

    // Getters & Setters
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSecurityPin() { return securityPin; }
    public void setSecurityPin(String securityPin) { this.securityPin = securityPin; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
