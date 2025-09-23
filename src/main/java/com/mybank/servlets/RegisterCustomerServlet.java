package com.mybank.servlets;

import com.mybank.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class RegisterCustomerServlet extends HttpServlet {

    private CustomerService service = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            int id = service.registerCustomer(fullname, email, password);

            if (id > 0) {
                response.sendRedirect(request.getContextPath() + "/register.jsp?success=true&customerId=" + id);
            } else if (id == -2) {
                response.sendRedirect(request.getContextPath() + "/register.jsp?exists=true");
            } else {
                response.sendRedirect(request.getContextPath() + "/register.jsp?error=true");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=true");
        }
    }
}
