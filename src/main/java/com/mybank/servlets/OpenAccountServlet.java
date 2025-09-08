package com.mybank.servlets;

import com.mybank.dao.AccountDAO;
import com.mybank.model.Account;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class OpenAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String accountType = request.getParameter("accountType");
        double deposit = Double.parseDouble(request.getParameter("deposit"));

        Account acc = new Account(customerId, accountType, deposit);
        AccountDAO dao = new AccountDAO();
        int accountId = dao.openAccount(acc);
        if (accountId > 0) {
            response.sendRedirect(request.getContextPath() + "/viewAccount.jsp?accountId=" + accountId);
        } else {
            response.getWriter().println("Failed to open account.");
        }
    }
}
