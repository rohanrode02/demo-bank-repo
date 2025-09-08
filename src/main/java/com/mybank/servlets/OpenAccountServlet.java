package com.mybank.servlets;

import com.mybank.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/OpenAccountServlet")
public class OpenAccountServlet extends HttpServlet {

    private AccountService accountService = new AccountService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            String accountType = request.getParameter("accountType");
            double deposit = Double.parseDouble(request.getParameter("deposit"));

            int newAccountId = accountService.openAccount(customerId, accountType, deposit);
            if (newAccountId > 0) {
                response.getWriter().println("Account opened, id = " + newAccountId);
            } else {
                response.getWriter().println("Failed to open account");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
