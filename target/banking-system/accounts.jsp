<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Open Account</title>
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
