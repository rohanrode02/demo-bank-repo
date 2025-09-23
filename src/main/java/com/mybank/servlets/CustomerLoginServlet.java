package com.mybank.servlets;

import com.mybank.model.Customer;
import com.mybank.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class CustomerLoginServlet extends HttpServlet {

    private CustomerService service = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Customer c = service.loginCustomer(email, password);
            if (c != null) {
                HttpSession session = request.getSession();
                session.setAttribute("loggedCustomer", c); // store customer in session
                response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/loginCustomer.jsp?error=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/loginCustomer.jsp?error=true");
        }
    }
}
