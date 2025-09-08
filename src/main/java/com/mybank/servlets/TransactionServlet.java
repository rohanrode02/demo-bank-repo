package com.mybank.servlets;

import com.mybank.dao.AccountDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class TransactionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int accountId = Integer.parseInt(request.getParameter("accountNumber"));
        String type = request.getParameter("transactionType");
        double amount = Double.parseDouble(request.getParameter("amount"));

        AccountDAO dao = new AccountDAO();
        double newBalance = dao.performTransaction(accountId, type, amount);

        if (!Double.isNaN(newBalance)) {
            response.getWriter().println("Transaction Successful! New Balance: " + newBalance);
        } else {
            response.getWriter().println("Transaction failed (insufficient funds or error).");
        }
    }
}
