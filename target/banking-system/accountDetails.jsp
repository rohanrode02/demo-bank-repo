<%@ page import="com.mybank.model.Account" %>
<%@ page import="com.mybank.model.Transaction" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Account Details</title>
    <style>
        body { font-family: Arial; text-align:center; background:#f4f7fb; }
        h2 { margin-top:30px; color:#2c3e50; }
        table { margin:20px auto; border-collapse: collapse; width:80%; }
        th, td { border:1px solid #ccc; padding:8px; }
        th { background:#3498db; color:white; }
    </style>
</head>
<body>
    <h2>Account Details</h2>

    <%
        Account account = (Account) request.getAttribute("account");
        List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");

        if(account != null){
    %>
        <p><strong>Account ID:</strong> <%= account.getAccountId() %></p>
        <p><strong>Account Type:</strong> <%= account.getAccountType() %></p>
        <p><strong>Balance:</strong> ₹<%= account.getBalance() %></p>

        <h3>Transaction History</h3>
        <table>
            <tr><th>Type</th><th>Amount</th><th>Date</th></tr>
            <%
                if(transactions != null && !transactions.isEmpty()){
                    for(Transaction t : transactions){
            %>
            <tr>
                <td><%= t.getTransactionType() %></td>
                <td>₹<%= t.getAmount() %></td>
                <td><%= t.getTransactionDate() %></td>
            </tr>
            <%      }
                } else { %>
            <tr><td colspan="3">No transactions found</td></tr>
            <% } %>
        </table>
    <% } else { %>
        <p style="color:red;">Account not found!</p>
    <% } %>

    <a href="viewAccount.jsp">Back to Search</a>
</body>
</html>
