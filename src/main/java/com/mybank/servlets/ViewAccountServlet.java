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
            request.getRequestDispatcher("/viewAccount.jsp").forward(request, response);
            return;
        }

        try {
            int accountId = Integer.parseInt(sid);
            Account account = accountService.getAccount(accountId);
            List<Transaction> transactions = accountService.getTransactionHistory(accountId);

            request.setAttribute("account", account);
            request.setAttribute("transactions", transactions);

            request.getRequestDispatcher("/accountDetails.jsp").forward(request, response);

        } catch (NumberFormatException nfe) {
            request.setAttribute("error", "Invalid Account ID");
            request.getRequestDispatcher("/viewAccount.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error fetching account details: " + e.getMessage());
            request.getRequestDispatcher("/viewAccount.jsp").forward(request, response);
        }
    }
}
