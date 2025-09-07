package com.mybank.servlets;

import java.io.IOException;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TransactionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int accountId = Integer.parseInt(request.getParameter("accountNumber"));
        String type = request.getParameter("transactionType");
        double amount = Double.parseDouble(request.getParameter("amount"));

        response.setContentType("text/html");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bankdb?useSSL=false&serverTimezone=UTC",
                "root", "root"
            );

            // ✅ balance safely fetch करा
            double balance = 0;
            PreparedStatement psBalance = con.prepareStatement(
                "SELECT balance FROM accounts WHERE account_id = ?"
            );
            psBalance.setInt(1, accountId);
            ResultSet rs = psBalance.executeQuery();

            if (rs.next()) {
                balance = rs.getDouble("balance");
            } else {
                response.getWriter().println("Account not found!");
                con.close();
                return;
            }

            // ✅ withdraw चेक
            if ("Withdraw".equalsIgnoreCase(type) && amount > balance) {
                response.getWriter().println("Insufficient Balance!");
                con.close();
                return;
            }

            // ✅ नवीन balance काढा
            double newBalance = type.equalsIgnoreCase("Deposit") 
                                ? balance + amount 
                                : balance - amount;

            // ✅ accounts टेबल update
            PreparedStatement psUpdate = con.prepareStatement(
                "UPDATE accounts SET balance=? WHERE account_id=?"
            );
            psUpdate.setDouble(1, newBalance);
            psUpdate.setInt(2, accountId);
            psUpdate.executeUpdate();

            // ✅ transaction history मध्ये insert
            PreparedStatement psInsert = con.prepareStatement(
                "INSERT INTO transactions(account_id, transaction_type, amount) VALUES(?,?,?)"
            );
            psInsert.setInt(1, accountId);
            psInsert.setString(2, type);
            psInsert.setDouble(3, amount);
            psInsert.executeUpdate();

            con.close();

            response.getWriter().println("Transaction Successful! New Balance: " + newBalance);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
