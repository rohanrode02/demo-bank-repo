<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>View Transaction</title>
    <style>
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

.error { color: red; font-weight: bold; }
    </style>
</head>
<body>
<h2>View Transaction</h2>

<form action="ViewTransactionServlet" method="post">
    Account Number: <input type="text" name="accountNumber" required><br><br>
    Security Pin: <input type="password" name="securityPin" required><br><br>
    <input type="submit" value="View Transactions">
</form>

<% if (request.getAttribute("error") != null) { %>
    <p class="error"><%= request.getAttribute("error") %></p>
<% } %>

<a href="dashboard.jsp">Home Page</a>
</body>
</html>
