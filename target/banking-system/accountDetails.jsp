<%@ page import="com.mybank.model.Account"%>
<%@ page import="com.mybank.model.Transaction"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Account Details</title>
   <style>
    /* General Page Style */
body {
    font-family: Arial, sans-serif;
    background: #f4f7fb;
    margin: 0;
    padding: 0;
    text-align: center;
}

/* Heading */
h2 {
    color: #2c3e50;
    margin-top: 30px;
}

/* Form Container */
form {
    background: #fff;
    padding: 20px;
    margin: 30px auto;
    width: 350px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
}

/* Inputs */
input[type="text"],
input[type="email"],
input[type="number"],
select {
    width: 90%;
    padding: 10px;
    margin: 8px 0;
    border: 1px solid #ccc;
    border-radius: 6px;
    font-size: 14px;
}

/* Submit Button */
input[type="submit"] {
    background-color: #3498db;
    color: white;
    border: none;
    padding: 12px;
    border-radius: 6px;
    width: 100%;
    font-size: 16px;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #2980b9;
}

/* Links */
a {
    margin: 10px;
    color: #3498db;
    text-decoration: none;
    font-weight: bold;
}

a:hover {
    text-decoration: underline;
}

   </style>
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
