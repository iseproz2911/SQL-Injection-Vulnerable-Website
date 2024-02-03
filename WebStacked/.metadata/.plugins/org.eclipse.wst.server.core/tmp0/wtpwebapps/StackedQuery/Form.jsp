<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="demo.MainServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Example Page</title>
    <style>
        body {
            background-image: url('https://images4.alphacoders.com/610/610757.jpg');
            background-size: cover;
            background-position: center;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        h2 {
            color: #333;
        }

        table {
            border-collapse: collapse;
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 15px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .detail-button {
            background-color: #008CBA;
            color: white;
            padding: 8px 12px;
            border: none;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div style="text-align: center; padding: 20px;">
        <h2 style="color: #fff;">StackedQuery</h2>
    </div>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Price</th>
            <th>Action</th> 
        </tr>
        <% 
            try {
                ResultSet resultSet = (ResultSet) request.getAttribute("resultSet");
                if (resultSet != null) {
                    while(resultSet.next()) {
        %>
                        <tr>
                            <td><%= resultSet.getString("ID") %></td>
                            <td><%= resultSet.getString("Name") %></td>
                            <td><%= resultSet.getInt("Quantity") %></td>
                            <td><%= resultSet.getBigDecimal("Price") %></td>
                            <td><a class="detail-button" href="detail.jsp?id=<%= resultSet.getInt("ID") %>">Detail</a></td>
                        </tr>
        <%
                    }
                } else {
                    out.println("<tr><td colspan='5'>ResultSet is null.</td></tr>");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>
    </table>
</body>
</html>