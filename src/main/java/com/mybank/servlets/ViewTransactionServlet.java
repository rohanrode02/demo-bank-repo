package com.mybank.servlets;

import com.mybank.model.Transaction;
import com.mybank.service.TransactionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ViewTransactionServlet extends HttpServlet {

    private TransactionService transactionService;

    @Override
    public void init() {
        transactionService = new TransactionService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountNumber = request.getParameter("accountNumber");
        String securityPin = request.getParameter("securityPin");

        try {
            // validate account
            boolean valid = transactionService.validateAccount(accountNumber, securityPin);

            if (valid) {
                double balance = transactionService.getAccountBalance(accountNumber);
                List<Transaction> transactions = transactionService.getTransactions(accountNumber);

                request.setAttribute("balance", balance);
                request.setAttribute("transactions", transactions);
                request.getRequestDispatcher("transactiondetail.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Account Number or Security Pin is wrong!");
                request.getRequestDispatcher("viewtransaction.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong: " + e.getMessage());
            request.getRequestDispatcher("viewtransaction.jsp").forward(request, response);
        }
    }
}
