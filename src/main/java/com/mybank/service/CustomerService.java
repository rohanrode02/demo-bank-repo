package com.mybank.service;

import com.mybank.dao.CustomerDAO;
import com.mybank.model.Customer;

public class CustomerService {
    private CustomerDAO customerDAO = new CustomerDAO();

    public int registerCustomer(String fullname, String email, String phone) throws Exception {
        Customer c = new Customer(fullname, email, phone);
        return customerDAO.createCustomer(c); // returns generated id or -1
    }
}
