<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Account</title>
    <style>
        body { font-family: Arial; text-align:center; background:#f4f7fb; }
        h2 { margin-top:30px; color:#2c3e50; }
        form { background:#fff; padding:20px; margin:30px auto; width:350px; border-radius:10px; }
        input[type="number"], input[type="submit"] { width:90%; padding:10px; margin:10px 0; }
        input[type="submit"] { background:#3498db; color:white; border:none; cursor:pointer; }
        input[type="submit"]:hover { background:#2980b9; }
    </style>
</head>
<body>
    <h2>View Account</h2>
    <form action="ViewAccountServlet" method="get">
        <label>Account ID:</label><br/>
        <input type="number" name="accountId" required/><br/>
        <input type="submit" value="View Details"/>
    </form>
    <%
        String error = (String) request.getAttribute("error");
        if(error != null){
    %>
        <p style="color:red;"><%= error %></p>
    <%
        }
    %>
</body>
</html>
