<%@ page import="com.mybank.model.Account"%>
<%@ page import="com.mybank.model.Transaction"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Details</title>
</head>
<body>
<h2>Account Details</h2>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
    <p style="color:red;"><%= error %></p>
<%
    } else {
        Account account = (Account) request.getAttribute("account");
        List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
%>
        <p><strong>Account ID:</strong> <%= account.getAccountId() %></p>
        <p><strong>Account Type:</strong> <%= account.getAccountType() %></p>
        <p><strong>Balance:</strong> ₹<%= account.getBalance() %></p>

        <h3>Transactions</h3>
        <table border="1" cellpadding="5" cellspacing="0">
            <tr><th>Type</th><th>Amount</th><th>Date</th></tr>
            <%
                for(Transaction t : transactions) {
            %>
                <tr>
                    <td><%= t.getTransactionType() %></td>
                    <td>₹<%= t.getAmount() %></td>
                    <td><%= t.getTransactionDate() %></td>
                </tr>
            <% } %>
        </table>
<% } %>

<a href="ViewAccount.jsp">Back to Search</a>
</body>
</html>
