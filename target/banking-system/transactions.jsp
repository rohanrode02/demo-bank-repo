<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Transaction</title>
</head>
<body>
<h2>Perform Transaction</h2>
<form action="TransactionServlet" method="post">
    Account Number: <input type="text" name="accountNumber" required><br>
    Transaction Type: 
    <select name="transactionType">
        <option value="Deposit">Deposit</option>
        <option value="Withdraw">Withdraw</option>
    </select><br>
    Amount: <input type="number" name="amount" required><br>
    <input type="submit" value="Submit">
</form>
<a href="register.jsp">Register Customer</a> | <a href="accounts.jsp">Open Account</a>
</body>
</html>
