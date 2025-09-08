<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Open Account</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Open Account</h2>
<form action="OpenAccountServlet" method="post">
    Customer ID: <input type="text" name="customerId" required><br>
    Account Type: 
    <select name="accountType">
        <option value="Saving">Saving</option>
        <option value="Current">Current</option>
    </select><br>
    Initial Deposit: <input type="number" name="deposit" required><br>
    <input type="submit" value="Open Account">
</form>
<a href="register.jsp">Register Customer</a> | <a href="transactions.jsp">Transactions</a>
</body>
</html>
