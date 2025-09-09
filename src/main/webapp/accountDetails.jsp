<%@ page import="com.mybank.model.Account"%>
<%@ page import="com.mybank.model.Transaction"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Details</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="container">
    <h2>Account Details</h2>

    <%
        Account account = (Account) request.getAttribute("account");
        List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
        if (account != null) {
    %>
        <p><strong>Account ID:</strong> <%= account.getAccountId() %></p>
        <p><strong>Account Type:</strong> <%= account.getAccountType() %></p>
        <p><strong>Balance:</strong> ₹<%= account.getBalance() %></p>

        <h3>Transaction History</h3>
        <div class="table-wrap">
        <table>
            <tr><th>Type</th><th>Amount</th><th>Date</th></tr>
            <%
                for (Transaction t : transactions) {
            %>
            <tr>
                <td><%= t.getTransactionType() %></td>
                <td>₹<%= t.getAmount() %></td>
                <td><%= t.getTransactionDate() %></td>
            </tr>
            <% } %>
        </table>
        </div>
    <% } else { %>
        <p class="error">Account not found!</p>
    <% } %>

    <a href="ViewAccount.jsp">Back to Account Search</a> | <a href="register.jsp">Register Customer</a>
</div>
</body>
</html>
