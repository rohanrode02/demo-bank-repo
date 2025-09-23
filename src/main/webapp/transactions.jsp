<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Transaction</title>
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
form input {
    width: 90%;
    padding: 10px;
    margin: 8px 0;
    border: 1px solid #ccc;
    border-radius: 6px;
    font-size: 14px;
}
/* Inputs */
form select{
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
<h2>Perform Transaction</h2>
<form action="TransactionServlet" method="post">
    Account Number: <input type="text" name="accountNumber" required><br>
    Security Pin: <input type="password" name="securityPin" required><br>
    Transaction Type: 
    <select name="transactionType">
        <option value="Deposit">Deposit</option>
        <option value="Withdraw">Withdraw</option>
    </select><br>
    Amount: <input type="number" name="amount" required><br>
    <input type="submit" value="Submit">
</form>

<a href="dashboard.jsp">Home Page</a> | <a href="accountDetails.jsp">Account Details</a>
</body>
</html>
