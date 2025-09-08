<%@ page import="java.util.List" %>
    <%@ page import="com.mybank.dao.AccountDAO.AccountDetails" %>
        <%@ page import="com.mybank.dao.AccountDAO.Transaction" %>
            <%@ page contentType="text/html;charset=UTF-8" language="java" %>
                <!DOCTYPE html>
                <html>

                <head>
                    <meta charset="UTF-8">
                    <title>Account Details</title>
                    <link rel="stylesheet" href="style.css">
                    <style>
                        table {
                            border-collapse: collapse;
                            width: 70%;
                            margin: 0 auto;
                            /* <-- Table ला horizontally center करतो */
                        }

                        th,
                        td {
                            border: 1px solid black;
                            padding: 8px;
                            text-align: left;
                        }

                        th {
                            background-color: #f2f2f2;
                        }

                        .error {
                            color: red;
                            text-align: center;
                            /* Error message center करतो */
                        }

                        h2,
                        h3 {
                            text-align: center;
                            /* Heading center करतो */
                        }
                    </style>
                </head>

                <body>

                    <h2>Account Details</h2>

                    <% AccountDetails account=(AccountDetails) request.getAttribute("account"); List<Transaction>
                        transactions = (List<Transaction>) request.getAttribute("transactions");

                            if (account != null) {
                            %>
                            <p><strong>Account ID:</strong>
                                <%= account.getAccountId() %>
                            </p>
                            <p><strong>Account Type:</strong>
                                <%= account.getAccountType() %>
                            </p>
                            <p><strong>Balance:</strong> ₹<%= account.getBalance() %>
                            </p>

                            <h3>Transaction History</h3>
                            <table>
                                <tr>
                                    <th>Type</th>
                                    <th>Amount</th>
                                    <th>Date</th>
                                </tr>
                                <% for (Transaction t : transactions) { %>
                                    <tr>
                                        <td>
                                            <%= t.getType() %>
                                        </td>
                                        <td>₹<%= t.getAmount() %>
                                        </td>
                                        <td>
                                            <%= t.getDate() %>
                                        </td>
                                    </tr>
                                    <% } %>
                            </table>
                            <% } else if (request.getParameter("accountId") !=null) { %>
                                <p class="error">Account not found!</p>
                                <% } else { %>
                                    <p>Please enter an Account ID in the form to view details.</p>
                                    <% } %>

                                        <a href="ViewAccount.jsp">Back to Account Search</a> | <a
                                            href="register.jsp">Register Customer</a>

                </body>

                </html>