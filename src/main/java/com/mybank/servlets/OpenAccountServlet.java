package com.mybank.servlets;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mybank.dao.AccountDAO;

public class OpenAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String accountType = request.getParameter("accountType");
        double deposit = Double.parseDouble(request.getParameter("deposit"));

        AccountDAO accountDAO = new AccountDAO();
        boolean success = accountDAO.openAccount(customerId, accountType, deposit);

        response.setContentType("text/html;charset=UTF-8");
        if (success) {
            response.getWriter().println("<h3 style='color:green;'>Account Opened Successfully!</h3>");
        } else {
            response.getWriter().println("<h3 style='color:red;'>Failed to open account!</h3>");
        }
    }
}
