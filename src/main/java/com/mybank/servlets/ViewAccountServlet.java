package com.mybank.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.mybank.dao.AccountDAO;
import com.mybank.dao.AccountDAO.AccountDetails;
import com.mybank.dao.AccountDAO.Transaction;

@WebServlet("/ViewAccountServlet")
public class ViewAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Get accountId parameter
            int accountId = Integer.parseInt(request.getParameter("accountId"));

            // Create DAO instance
            AccountDAO dao = new AccountDAO();

            // Fetch account details and transaction history
            AccountDetails account = dao.getAccountDetails(accountId);
            List<Transaction> transactions = dao.getTransactionHistory(accountId);

            // Set as request attributes
            request.setAttribute("account", account);
            request.setAttribute("transactions", transactions);

            // Forward to JSP for display
            request.getRequestDispatcher("/viewAccount.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.getWriter().println("<h3 style='color:red;'>Invalid Account ID!</h3>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3 style='color:red;'>Error: " + e.getMessage() + "</h3>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // For simplicity, call doGet
        doGet(request, response);
    }
}
