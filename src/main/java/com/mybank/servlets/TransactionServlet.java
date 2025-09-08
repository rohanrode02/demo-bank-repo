package com.mybank.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mybank.dao.TransactionDAO;

public class TransactionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int accountId = Integer.parseInt(request.getParameter("accountNumber"));
        String type = request.getParameter("transactionType");
        double amount = Double.parseDouble(request.getParameter("amount"));

        TransactionDAO dao = new TransactionDAO();
        response.setContentType("text/html");

        try {
            double balance = dao.getBalance(accountId);

            if (balance == -1) {
                response.getWriter().println("Account not found!");
                return;
            }

            if ("Withdraw".equalsIgnoreCase(type) && amount > balance) {
                response.getWriter().println("Insufficient Balance!");
                return;
            }

            double newBalance = type.equalsIgnoreCase("Deposit") 
                                ? balance + amount 
                                : balance - amount;

            boolean updated = dao.updateBalance(accountId, newBalance);
            boolean inserted = dao.insertTransaction(accountId, type, amount);

            if (updated && inserted) {
                response.getWriter().println("Transaction Successful! New Balance: " + newBalance);
            } else {
                response.getWriter().println("Transaction Failed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
