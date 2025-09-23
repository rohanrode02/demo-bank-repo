package com.mybank.servlets;

import com.mybank.model.Transaction;
import com.mybank.service.TransactionService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class TransactionServlet extends HttpServlet {

    private TransactionService transactionService = new TransactionService();

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String accountNumber = request.getParameter("accountNumber");
    String securityPin = request.getParameter("securityPin"); // <-- new
    String transactionType = request.getParameter("transactionType");
    double amount = Double.parseDouble(request.getParameter("amount"));

    Transaction transaction = new Transaction();
    transaction.setAccountNumber(accountNumber);
    transaction.setTransactionType(transactionType);
    transaction.setAmount(amount);

    try {
        transactionService.performTransaction(transaction, securityPin);
        response.sendRedirect("success.jsp");
    } catch (Exception e) {
        request.setAttribute("errorMessage", e.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
}

}
