package com.mybank.model;

import java.sql.Timestamp;

public class Transaction {
    private int transactionId;
    private String transactionType;
    private double amount;
    private Timestamp transactionDate;
    private String accountNumber;

    // --- Constructor ---
    public Transaction() {
    }

    public Transaction(int transactionId, String transactionType, double amount, Timestamp transactionDate, String accountNumber) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
        this.accountNumber = accountNumber;
    }

    // --- Getters & Setters ---
    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Timestamp getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Timestamp transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // --- toString() for debugging ---
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", transactionDate=" + transactionDate +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
