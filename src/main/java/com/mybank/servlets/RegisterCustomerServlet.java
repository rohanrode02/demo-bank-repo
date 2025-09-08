package com.mybank.servlets;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mybank.util.DBConnection; // ✅ utility import

public class RegisterCustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        try (Connection con = DBConnection.getConnection()) { // ✅ utility वापर
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO customers(fullname,email,phone) VALUES(?,?,?)"
            );
            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, phone);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                response.sendRedirect(request.getContextPath() + "/register.jsp?success=true");
            } else {
                response.sendRedirect(request.getContextPath() + "/register.jsp?error=true");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/register.jsp?error=true");
        }
    }
}
