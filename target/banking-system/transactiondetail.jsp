<%@ page import="java.util.List" %>
<%@ page import="com.mybank.model.Transaction" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Transaction Details</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f7fb; text-align: center; }
        h2 { color: #2c3e50; margin-top: 20px; }
        table { margin: 20px auto; border-collapse: collapse; width: 80%; }
        table, th, td { border: 1px solid #ccc; padding: 10px; }
        th { background: #3498db; color: white; }
    </style>
</head>
<body>
<h2>Transaction Details</h2>

<% if (request.getAttribute("balance") != null) { %>
    <h3>Account Balance: ₹ <%= request.getAttribute("balance") %></h3>
<% } %>

<%
    List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
    if (transactions != null && !transactions.isEmpty()) {
%>
    <table>
        <tr>
            <th>ID</th>
            <th>Type</th>
            <th>Amount</th>
            <th>Date</th>
        </tr>
        <% for (Transaction t : transactions) { %>
        <tr>
            <td><%= t.getTransactionId() %></td>
            <td><%= t.getTransactionType() %></td>
            <td><%= t.getAmount() %></td>
            <td><%= t.getTransactionDate() %></td>
        </tr>
        <% } %>
    </table>
<% } else { %>
    <p>No transactions found for this account.</p>
<% } %>

<a href="viewtransaction.jsp">Back</a> | <a href="dashboard.jsp">Home</a>
</body>
</html>
