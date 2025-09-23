<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f7fb;
            text-align: center;
            padding-top: 50px;
        }
        form {
            background: #fff;
            padding: 20px;
            margin: auto;
            width: 350px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        input[type="email"], input[type="password"] {
            width: 90%;
            padding: 10px;
            margin: 8px 0;
            border-radius: 6px;
            border: 1px solid #ccc;
        }
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
        p { color:red; }
    </style>
</head>
<body>
<h2>Customer Login</h2>

<% if("true".equals(request.getParameter("error"))) { %>
    <p>Invalid email or password!</p>
<% } %>

<form action="${pageContext.request.contextPath}/CustomerLoginServlet" method="post">
    Email: <input type="email" name="email" required><br>
    Password: <input type="password" name="password" required><br><br>
    <input type="submit" value="Login">
</form>

<a href="register.jsp">Register Here</a>
</body>
</html>
