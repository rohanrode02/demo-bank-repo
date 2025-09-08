<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mybank.dao.AccountDAO.AccountDetails" %>
<%@ page import="com.mybank.dao.AccountDAO.Transaction" %>
<%@ page import="java.util.List" %>
<%
    AccountDetails account = (AccountDetails) request.getAttribute("account");
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
            margin: 20px;
        }
        h2, h3 {
            color: #333;
        }
        .account-info, .transactions {
            background-color: #fff;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
        tr:nth-child(even) {background-color: #f9f9f9;}
        .no-data {
            color: red;
        }
    </style>
</head>
<body>

<h2>Account Details</h2>

<div class="account-info">
    <%
        if(account != null) {
    %>
        <p><strong>Account ID:</strong> <%= account.getAccountId() %></p>
        <p><strong>Account Type:</strong> <%= account.getAccountType() %></p>
        <p><strong>Balance:</strong> ₹<%= account.getBalance() %></p>
    <%
        } else {
    %>
        <p class="no-data">Account not found!</p>
    <%
        }
    %>
</div>

<div class="transactions">
    <h3>Transaction History</h3>
    <%
        if(transactions != null && !transactions.isEmpty()) {
    %>
    <table>
        <tr>
            <th>Type</th>
            <th>Amount</th>
            <th>Date</th>
        </tr>
        <%
            for(Transaction t : transactions) {
        %>
        <tr>
            <td><%= t.getType() %></td>
            <td>₹<%= t.getAmount() %></td>
            <td><%= t.getDate() %></td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        } else {
    %>
        <p class="no-data">No transactions found!</p>
    <%
        }
    %>
</div>

</body>
</html>
