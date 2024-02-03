<%@ page import="java.util.List" %>
<%@ page import="entity.Product" %> 
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BlindSQLinject</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #ecf0f1;
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        header {
            background-color: #3498db;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        table {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            border: 1px solid #ddd;
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #333;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        h2 {
            color: #fff;
            text-align: center;
        }

        a {
            color: #3498db;
            text-decoration: none;
        }

        a:hover {
            color: #2980b9;
        }
    </style>
</head>

<body>
    <header>
        <h2>Welcome, <%= session.getAttribute("username") %>!</h2>
    </header>

    <% 
        try {
            List<Product> productList = (List<Product>) request.getAttribute("productList");

            if (productList != null && !productList.isEmpty()) {
    %>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>NameSP</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>

                    <% 
                        for (Product product : productList) {
                    %>
                            <tr>
                                <td><%= product.getId() %></td>
                                <td><%= product.getNameSP() %></td>
                                <td><%= product.getPrice() %></td>
                                <td><a href="ProductDetail?id=<%= product.getId() %>">View Detail</a></td>
                            </tr>
                    <% 
                        }
                    %>
                </table>
    <% 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    %>

</body>

</html>
