<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mybank.model.Account" %>
<%
    Account account = (Account) request.getAttribute("account");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Account Created</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Account Created Successfully!</h2>
    <div class="card mt-3 p-3">
        <p><strong>Account Number:</strong> <%= account.getAccountNumber() %></p>
        <p><strong>Full Name:</strong> <%= account.getFullname() %></p>
        <p><strong>Email:</strong> <%= account.getEmail() %></p>
        <p><strong>Balance:</strong> $<%= account.getBalance() %></p>
    </div>
    <a href="dashboard.jsp" class="btn btn-success mt-3">Back to Dashboard</a>
</div>
</body>
</html>
