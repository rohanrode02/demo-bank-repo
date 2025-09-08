package com.mybank.servlets;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mybank.util.DBConnection; // ✅ utility import

public class OpenAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String accountType = request.getParameter("accountType");
        double deposit = Double.parseDouble(request.getParameter("deposit"));

        try (Connection con = DBConnection.getConnection()) { // ✅ utility वापर
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO accounts(customer_id, account_type, balance) VALUES(?,?,?)"
            );
            ps.setInt(1, customerId);
            ps.setString(2, accountType);
            ps.setDouble(3, deposit);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                response.getWriter().println("<h3 style='color:green;'>Account Opened Successfully!</h3>");
            } else {
                response.getWriter().println("<h3 style='color:red;'>Failed to open account!</h3>");
            }
        } catch(Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
        }
    }
}
