package com.mybank.servlets;

import com.mybank.dao.CustomerDAO;
import com.mybank.model.Customer;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegisterCustomerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Customer c = new Customer(fullname, email, phone);
        CustomerDAO dao = new CustomerDAO();
        int id = dao.registerCustomer(c);
        if (id > 0) {
            response.sendRedirect(request.getContextPath() + "/register.jsp?success=true&customerId=" + id);
        } else {
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=true");
        }
    }
}
