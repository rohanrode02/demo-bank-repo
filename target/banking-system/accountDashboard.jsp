<%@ page import="com.mybank.model.Account" %>
<%
    Account acc = (Account) session.getAttribute("loggedAccount");
    if (acc == null) {
        response.sendRedirect("loginAccount.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Account Dashboard</title>
</head>
<body>
<h2>Welcome, <%= acc.getFullname() %></h2>
<p>Email: <%= acc.getEmail() %></p>
<p>Balance: <%= acc.getBalance() %></p>

<h3>Deposit/Withdraw</h3>
<form action="TransactionServlet" method="post">
    Amount: <input type="number" step="0.01" name="amount" required><br>
    <select name="type">
        <option value="deposit">Deposit</option>
        <option value="withdraw">Withdraw</option>
    </select><br>
    <input type="submit" value="Submit">
</form>

<c:if test="${not empty msg}">
    <p style="color:green">${msg}</p>
</c:if>

<a href="logout.jsp">Logout</a>
</body>
</html>
