package demo;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainServlet", urlPatterns = "/MainServletDemo")
public class MainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(
                    "jdbc:sqlserver://MinhQuan\\SQLEXPRESS01:1433;databaseName=stacksql", "sa", "123456789@");

            // Retrieve all products from the database
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Product");

            // Set the result set as a request attribute
            request.setAttribute("resultSet", resultSet);

            // Forward to the example.jsp page
            request.getRequestDispatcher("Form.jsp").forward(request, response);

            // Close resources
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getProductDetailsById(String productId) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(
                    "jdbc:sqlserver://MinhQuan\\SQLEXPRESS01:1433;databaseName=stacksql", "sa", "123456789@");
            System.out.println("Connection successful");

//Ban!
            String[] filter = { "SELECT"}; //,"AND","UPDATE","DROP","UNION",
            for (String banned : filter) {
                if (productId.toUpperCase().contains(banned)) {
                    System.out.println("Hacker detected");
                    return null; 
                }
            }

            String sql = "SELECT * FROM Product WHERE ID = " + productId;

            // Create a statement
            statement = connection.createStatement();

            // Execute the query
            resultSet = statement.executeQuery(sql);


            return resultSet;
        } finally {
        }
    }
}

