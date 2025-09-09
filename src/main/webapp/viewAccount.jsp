<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Account</title>
</head>
<body>
<h2>View Account</h2>
<form action="ViewAccountServlet" method="get">
    <label>Account ID:</label>
    <input type="number" name="accountId" required/>
    <input type="submit" value="View Account"/>
</form>
</body>
</html>
