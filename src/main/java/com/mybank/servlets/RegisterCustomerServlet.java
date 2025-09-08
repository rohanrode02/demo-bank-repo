package com.mybank.servlets;

import com.mybank.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/RegisterCustomerServlet")
public class RegisterCustomerServlet extends HttpServlet {

    private CustomerService service = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        try {
            int id = service.registerCustomer(fullname, email, phone);
            if (id > 0) {
                response.sendRedirect(request.getContextPath() + "/register.jsp?success=true&customerId=" + id);
            } else {
                response.sendRedirect(request.getContextPath() + "/register.jsp?error=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=true");
        }
    }
}
