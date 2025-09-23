<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register Customer</title>
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
input[type="password"],
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
<h2>Register Customer</h2>

<!-- Success / Error message -->

<% if("true".equals(request.getParameter("success"))) { %>
    <p style="color:green;">Customer registered successfully!</p>
<% } else if("true".equals(request.getParameter("error"))) { %>
    <p style="color:red;">Error occurred. Try again.</p>
<% } else if("true".equals(request.getParameter("exists"))) { %>
    <p style="color:red;">User Already Exists for this Email Address!!</p>
<% } %>

<form action="${pageContext.request.contextPath}/RegisterCustomerServlet" method="post">
    Full Name: <input type="text" name="fullname" required><br>
    Email: <input type="email" name="email" required><br>
    Password: <input type="password" name="password" required>
    <input type="submit" value="Register">
</form>

<a href="loginCustomer.jsp">Logins</a>
</body>
</html>
