<%@ page import="com.mybank.model.Account" %>
<%@ page import="com.mybank.model.Transaction" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Details</title>
    <link rel="stylesheet" href="style.css">
    <style>
        .container { max-width: 900px; margin: 40px auto; font-family: Arial, sans-serif; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background:#f7f7f7; }
        .center { text-align:center; }
        .error { color: red; }
    </style>
</head>
<body>
<div class="container">
    <h2 class="center">Account Details</h2>

<%
    Account account = (Account) request.getAttribute("account");
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");

    if (account != null) {
%>
    <p><strong>Account ID:</strong> <%= account.getAccountId() %></p>
    <p><strong>Account Type:</strong> <%= account.getAccountType() %></p>
    <p><strong>Balance:</strong> ₹<%= account.getBalance() %></p>

    <h3>Transaction History</h3>
    <table>
        <tr>
            <th>Type</th>
            <th>Amount</th>
            <th>Date</th>
        </tr>
    <%
        if (transactions != null && !transactions.isEmpty()) {
            for (Transaction t : transactions) {
    %>
        <tr>
            <td><%= t.getType() %></td>
            <td>₹<%= t.getAmount() %></td>
            <td><%= t.getDate() %></td>
        </tr>
    <%
            }
        } else {
    %>
        <tr><td colspan="3" class="center">No transactions found</td></tr>
    <%
        }
    %>
    </table>
<%
    } else {
%>
    <p class="error">Account not found. वापरुन पहा की Account ID बरोबर टाकला आहे की नाही.</p>
<%
    }
%>

    <p><a href="ViewAccount.jsp">Back to Search</a> | <a href="register.jsp">Register Customer</a></p>
</div>
</body>
</html>
