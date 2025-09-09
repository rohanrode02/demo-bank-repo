package com.mybank.servlets;

import com.mybank.model.Account;
import com.mybank.model.Transaction;
import com.mybank.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;

public class ViewAccountServlet extends HttpServlet {

    private AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sid = request.getParameter("accountId");

        if (sid == null || sid.isBlank()) {
            request.getRequestDispatcher("/accountDetails.jsp").forward(request, response);
            return;
        }

        try {
            int accountId = Integer.parseInt(sid);
            System.out.println("Fetching account ID: " + accountId); // debug

            Account account = accountService.getAccount(accountId);

            if (account == null) {
                System.out.println("Account not found in DB!"); // debug
                request.setAttribute("error", "Account not found!");
            } else {
                List<Transaction> transactions = accountService.getTransactionHistory(accountId);
                request.setAttribute("account", account);
                request.setAttribute("transactions", transactions);
            }

            RequestDispatcher rd = request.getRequestDispatcher("/accountDetails.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/accountDetails.jsp").forward(request, response);
        }
    }
}
