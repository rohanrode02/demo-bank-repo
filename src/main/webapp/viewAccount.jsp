<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Account</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <div class="container">
        <h2>View Account</h2>
        <form action="ViewAccountServlet" method="get">
            <label>Account ID:</label>
            <input type="number" name="accountId" required />
            <input type="submit" value="View Account Details" />
        </form>
        <br/>
        <a href="register.jsp">Register Customer</a> | <a href="transactions.jsp">Transactions</a>
    </div>
</body>
</html>
