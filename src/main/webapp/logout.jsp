<%@ page language="java" %>
<%
    session.invalidate();
    response.sendRedirect("loginCustomer.jsp");
%>
