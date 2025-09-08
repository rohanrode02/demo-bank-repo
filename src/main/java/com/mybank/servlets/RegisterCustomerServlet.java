package com.mybank.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mybank.dao.CustomerDAO;

public class RegisterCustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        CustomerDAO customerDAO = new CustomerDAO();
        boolean success = customerDAO.registerCustomer(fullname, email, phone);

        if (success) {
            response.sendRedirect(request.getContextPath() + "/register.jsp?success=true");
        } else {
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=true");
        }
    }
}
