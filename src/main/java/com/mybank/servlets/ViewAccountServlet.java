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
        System.out.println("AccountId received in servlet: " + sid);

        if (sid == null || sid.isBlank()) {
            request.getRequestDispatcher("/ViewAccount.jsp").forward(request, response);
            return;
        }

        try {
            int accountId = Integer.parseInt(sid);
            Account account = accountService.getAccount(accountId);
            System.out.println("Account fetched: " + account);

            if(account == null){
                System.out.println("Account is null for id: " + accountId);
            }

            List<Transaction> transactions = accountService.getTransactionHistory(accountId);
            System.out.println("Transactions fetched: " + (transactions != null ? transactions.size() : 0));

            request.setAttribute("account", account);
            request.setAttribute("transactions", transactions);
            RequestDispatcher rd = request.getRequestDispatcher("/accountDetails.jsp");
            rd.forward(request, response);

        } catch (NumberFormatException nfe) {
            System.out.println("Invalid accountId: " + sid);
            request.setAttribute("error", "Invalid Account ID");
            request.getRequestDispatcher("/viewAccount.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/viewAccount.jsp").forward(request, response);
        }
    }
}
