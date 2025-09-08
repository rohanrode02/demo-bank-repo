<%@ page import="java.util.List" %>
<%@ page import="com.mybank.dao.AccountDAO.AccountDetails" %>
<%@ page import="com.mybank.dao.AccountDAO.Transaction" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Account Details</title>
     <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 20px;
        }
        h2, h3 {
            color: #333;
        }
        form {
            margin-bottom: 20px;
        }
        input[type="number"], input[type="submit"] {
            padding: 8px;
            margin: 5px 0;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        table, th, td {
            border: 1px solid #aaa;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f0f0f0;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h2>View Account Details</h2>

<form action="ViewAccountServlet" method="get">
    <label>Account ID:</label>
    <input type="number" name="accountId" required />
    <input type="submit" value="View Details" />
</form>

<%
    AccountDetails account = (AccountDetails) request.getAttribute("account");
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
                for (Transaction t : transactions) {
            %>
            <tr>
                <td><%= t.getType() %></td>
                <td>₹<%= t.getAmount() %></td>
                <td><%= t.getDate() %></td>
            </tr>
            <% } %>
        </table>
<%
    } else if (request.getParameter("accountId") != null) {
%>
        <p class="error">Account not found!</p>
<%
    }
%>

</body>
</html>
