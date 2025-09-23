package com.mybank.servlets;

import com.mybank.model.Account;
import com.mybank.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

public class OpenAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String pin = request.getParameter("pin");

        Account account = new Account();
        account.setFullname(fullname);
        account.setEmail(email);
        account.setSecurityPin(pin);
        account.setBalance(0);

        AccountService service = new AccountService();
        String result = service.openAccount(account);

        if ("duplicate".equals(result)) {
            request.setAttribute("error", "Account already exists with this Email");
            request.getRequestDispatcher("openAccount.jsp").forward(request, response);
        } else if ("success".equals(result)) {
            request.setAttribute("account", account);
            request.getRequestDispatcher("accountSuccess.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Account creation failed!");
            request.getRequestDispatcher("openAccount.jsp").forward(request, response);
        }
    }
}
