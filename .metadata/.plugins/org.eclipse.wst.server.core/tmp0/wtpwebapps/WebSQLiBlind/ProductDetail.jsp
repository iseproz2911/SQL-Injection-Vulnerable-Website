<%@ page import="java.io.*,java.util.*,entity.Product" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Detail</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: url('https://i.pinimg.com/originals/46/34/13/4634134fb0b7d7af8cf99c3bad00606c.jpg') no-repeat center center fixed;
            background-size: cover;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }

        header {
            background-color: #3498db;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        h2 {
            color: #fff;
        }

        table {
            width: 70%;
            margin-top: 20px;
            border-collapse: separate;
            border-spacing: 0 15px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            border: 1px solid #ddd;
            padding: 15px;
            text-align: left;
        }

        th {
            background-color: #3498db;
            color: #fff;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
    <div align="center">
        <header>
            <h2>Product Detail</h2>
        </header>
        
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Description</th>

            </tr>
            <tr>
                <td>${productID}</td>
                <td>${name}</td>
                <td>${price}</td>
                <td>${description}</td>

            </tr>
        </table>
    </div>
</body>
</html>