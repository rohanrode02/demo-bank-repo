package com.mybank.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mybank.dao.AccountDAO;
import com.mybank.dao.AccountDAO.AccountDetails;
import com.mybank.dao.AccountDAO.Transaction;
import java.util.List;

public class ViewAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int accountId = Integer.parseInt(request.getParameter("accountId"));

        AccountDAO dao = new AccountDAO();
        AccountDetails account = dao.getAccountDetails(accountId);
        List<Transaction> transactions = dao.getTransactionHistory(accountId);

        // Servlet मध्ये response print न करता, JSP page ला forward करणे
        request.setAttribute("account", account);
        request.setAttribute("transactions", transactions);

        // JSP page ला forward
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewAccount.jsp");
        dispatcher.forward(request, response);
    }
}
