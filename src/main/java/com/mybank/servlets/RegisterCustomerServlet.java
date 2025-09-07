package com.mybank.servlets;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegisterCustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bankdb?useSSL=false&serverTimezone=UTC",
                "root", "root"
            );

            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO customers(fullname,email,phone) VALUES(?,?,?)"
            );
            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, phone);

            int rows = ps.executeUpdate();
            con.close();

            if (rows > 0) {
                // यशस्वी नोंदणी झाल्यावर register.jsp वर redirect
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
