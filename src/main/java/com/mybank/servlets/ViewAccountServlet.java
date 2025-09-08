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

        response.setContentType("text/html;charset=UTF-8");
        StringBuilder html = new StringBuilder();
        html.append("<h2>Account Details</h2>");
        if (account != null) {
            html.append("<p>Account ID: ").append(account.getAccountId()).append("</p>");
            html.append("<p>Account Type: ").append(account.getAccountType()).append("</p>");
            html.append("<p>Balance: ").append(account.getBalance()).append("</p>");

            html.append("<h3>Transaction History</h3>");
            html.append("<table border='1'><tr><th>Type</th><th>Amount</th><th>Date</th></tr>");
            for (Transaction t : transactions) {
                html.append("<tr>")
                    .append("<td>").append(t.getType()).append("</td>")
                    .append("<td>").append(t.getAmount()).append("</td>")
                    .append("<td>").append(t.getDate()).append("</td>")
                    .append("</tr>");
            }
            html.append("</table>");
        } else {
            html.append("<p>Account not found!</p>");
        }

        response.getWriter().println(html.toString());
    }
}
