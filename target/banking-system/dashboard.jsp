<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mybank.model.Customer" %>
<%
    Customer c = (Customer) session.getAttribute("loggedCustomer");
    if (c == null) {
        response.sendRedirect("loginCustomer.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <!-- Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background: #f7f9fc;
            font-family: Arial, sans-serif;
        }
        .dashboard-container {
            max-width: 700px;
            margin: 100px auto;
            background: #fff;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            text-align: center;
        }
        h2 {
            margin-bottom: 20px;
            font-weight: bold;
        }
        p {
            margin-bottom: 30px;
            color: #555;
        }
        .btn-custom {
            margin: 10px;
            padding: 12px 24px;
            font-size: 16px;
            border-radius: 8px;
        }
    </style>
</head>
<body>

<div class="dashboard-container">
    <h2>Welcome, <%= c.getFullname() %> 🎉</h2>
    <p>Your email: <%= c.getEmail() %></p>

    <!-- Buttons -->
    <a href="openAccount.jsp" class="btn btn-primary btn-custom">🏦 Open New Bank Account</a>
    <a href="transactions.jsp" class="btn btn-success btn-custom">Transactions</a>
    <a href="viewtransaction.jsp" class="btn btn-danger btn-custom">View TransactionId</a>
    <a href="logout.jsp" class="btn btn-danger btn-custom">🚪 Logout</a>
</div>

</body>
</html>
