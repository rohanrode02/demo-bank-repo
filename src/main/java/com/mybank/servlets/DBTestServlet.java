package com.mybank.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DBTestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        try {
            String url = "jdbc:mysql://localhost:3306/bankdb?useSSL=false&serverTimezone=UTC";
            String username = "root";       
            String password = "root";       

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, username, password);

            if (conn != null) {
                response.getWriter().println("Database connection successful!");
                conn.close();
            } else {
                response.getWriter().println("Failed to connect database.");
            }

        } catch (ClassNotFoundException e) {
            response.getWriter().println("Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            response.getWriter().println("SQL Exception: " + e.getMessage());
        }
    }
}
