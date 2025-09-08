package com.mybank.model;

import java.util.Date;

public class Transaction {
    private int transactionId;
    private int accountId;
    private String type;
    private double amount;
    private Date date;

    // ✅ Default constructor (JPA / frameworks साठी गरजेचं)
    public Transaction() {}

    // ✅ Parameterized constructor (DAO साठी use करायला सोपं)
    public Transaction(int transactionId, int accountId, String type, double amount, Date date) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    // Getters & Setters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}
