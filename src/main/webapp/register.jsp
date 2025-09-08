<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register Customer</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h2>Register Customer</h2>

<!-- Success / Error message -->
<% if("true".equals(request.getParameter("success"))) { %>
    <p style="color:green;">Customer registered successfully!</p>
<% } else if("true".equals(request.getParameter("error"))) { %>
    <p style="color:red;">Error occurred. Try again.</p>
<% } %>

<form action="${pageContext.request.contextPath}/RegisterCustomerServlet" method="post">
    Full Name: <input type="text" name="fullname" required><br>
    Email: <input type="email" name="email" required><br>
    Phone: <input type="text" name="phone" required><br>
    <input type="submit" value="Register">
</form>

<a href="accounts.jsp">Open Account</a> | <a href="transactions.jsp">Transactions</a>
</body>
</html>
