package com.mybank.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mybank.dao.AccountDAO;
import com.mybank.dao.AccountDAO.AccountDetails;
import com.mybank.dao.AccountDAO.Transaction;

public class ViewAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int accountId = Integer.parseInt(request.getParameter("accountId"));

        AccountDAO dao = new AccountDAO();
        AccountDetails account = dao.getAccountDetails(accountId);
        List<Transaction> transactions = dao.getTransactionHistory(accountId);

        // Request attributes मध्ये data set करा
        request.setAttribute("account", account);
        request.setAttribute("transactions", transactions);

        // JSP file ला forward करा
        RequestDispatcher dispatcher = request.getRequestDispatcher("ViewAccountDetails.jsp");
        dispatcher.forward(request, response);
    }
}
