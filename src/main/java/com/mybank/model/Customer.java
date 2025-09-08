package com.mybank.model;

public class Customer {
    private int customerId;
    private String fullname;
    private String email;
    private String phone;

    public Customer() {}

    public Customer(int customerId, String fullname, String email, String phone) {
        this.customerId = customerId;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }

    public Customer(String fullname, String email, String phone) {
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
    }

    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
