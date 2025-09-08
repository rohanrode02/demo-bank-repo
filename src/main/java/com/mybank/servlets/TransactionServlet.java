package com.mybank.servlets;

import com.mybank.service.TransactionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/TransactionServlet")
public class TransactionServlet extends HttpServlet {

    private TransactionService transactionService = new TransactionService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int accountId = Integer.parseInt(request.getParameter("accountNumber"));
        String type = request.getParameter("transactionType");
        double amount = Double.parseDouble(request.getParameter("amount"));

        try {
            double newBalance = transactionService.performTransaction(accountId, type, amount);
            response.getWriter().println("Success. New balance: " + newBalance);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Transaction failed: " + e.getMessage());
        }
    }
}
