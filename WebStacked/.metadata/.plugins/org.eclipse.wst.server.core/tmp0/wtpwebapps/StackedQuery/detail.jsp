<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="demo.MainServlet" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Detail</title>
    <style>
        body {
            background-image: url('https://wallpaperwaifu.com/wp-content/uploads/2022/06/goku-ultra-instinct-dragon-ball-thumb.jpg');
            background-size: cover;
            background-position: center;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        h2 {
            text-align: center;
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
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        .error-message {
            text-align: center;
            color: #FF0000;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h2>Product Detail</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Quantity</th>
            <th>Price</th>
        </tr>
        <% 
            ResultSet resultSet = null;

            try {
                String productId = request.getParameter("id");
                System.out.println("Product ID in detail.jsp: " + productId);
                 
                MainServlet exampleServlet = new MainServlet();
                resultSet = exampleServlet.getProductDetailsById(productId);

                if (resultSet != null && resultSet.next()) {
        %>
                    <tr>
                        <td><%= resultSet.getString("ID") %></td>
                        <td><%= resultSet.getString("Name") %></td>
                        <td><%= resultSet.getInt("Quantity") %></td>
                        <td><%= resultSet.getBigDecimal("Price") %></td>
                    </tr>
        <%
                } else {
        %>
                    <tr>
                        <td colspan="4" class="error-message">Product details not found.</td>
                    </tr>
        <%
                }
            } catch (SQLException e) {
                 e.printStackTrace();
        %>
                <tr>
                    <td colspan="4" class="error-message">Error retrieving product details.</td>
                </tr>
        <%
            } finally {
                if (resultSet != null) {
                    resultSet.close();
                }

            }
        %>
    </table>
</body>
</html>

