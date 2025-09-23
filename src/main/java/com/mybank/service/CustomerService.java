package com.mybank.service;

import com.mybank.dao.CustomerDAO;
import com.mybank.model.Customer;

public class CustomerService {

    // declare object properly
    private CustomerDAO customerDAO = new CustomerDAO();

    public Customer loginCustomer(String email, String password) throws Exception {
        return customerDAO.login(email, password);
    }

    public int registerCustomer(String fullname, String email, String password) throws Exception {
        Customer c = new Customer(fullname, email, password);
        return customerDAO.createCustomer(c);
    }
}
