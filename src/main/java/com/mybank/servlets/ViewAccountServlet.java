package com.mybank.servlets;

import com.mybank.dao.AccountDAO;
import com.mybank.model.Account;
import com.mybank.model.Transaction;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ViewAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("accountId");
        if (idStr == null || idStr.trim().isEmpty()) {
            // अगर accountId नसेल तर फक्त search form दाखवायचं आहे — forward करा
            request.getRequestDispatcher("/ViewAccount.jsp").forward(request, response);
            return;
        }

        int accountId = Integer.parseInt(idStr);
        AccountDAO dao = new AccountDAO();
        Account account = dao.getAccountDetails(accountId);
        List<Transaction> transactions = dao.getTransactionHistory(accountId);

        request.setAttribute("account", account);
        request.setAttribute("transactions", transactions);

        // JSP मध्ये पहा (accountDetails.jsp)
        request.getRequestDispatcher("/accountDetails.jsp").forward(request, response);
    }
}
