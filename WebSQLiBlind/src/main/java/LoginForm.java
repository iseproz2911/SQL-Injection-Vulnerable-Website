import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.Product;

@WebServlet(name = "LoginForm", urlPatterns = {"/LoginForm"})
public class LoginForm extends HttpServlet {
    private static final long serialVersionUID = -3727179258077207351L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String jdbcUrl = "jdbc:mysql://localhost:3306/dbblindsql";
        String dbUser = "root";
        String dbPassword = "123456789@";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            Statement stmt = conn.createStatement();
//SQLi!!!
            String sql = "SELECT * FROM dbblindsql.userinfo WHERE username = '" + username + "' AND password='" + password + "'";
            System.out.println("Executing query: " + sql);
//          String sql = "SELECT * FROM dbblindsql.userinfo WHERE username = ? AND password = ?";
            ResultSet resultSet = stmt.executeQuery(sql);
//          PreparedStatement pstmt = conn.prepareStatement(sql)) {
//                pstmt.setString(1, username);
//                pstmt.setString(2, password);
            if (resultSet.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                sql = "SELECT * FROM dbblindsql.Product";
                resultSet = stmt.executeQuery(sql);

                List<Product> productList = new ArrayList<>();
                while (resultSet.next()) {
                    Product product = new Product();
                    product.setId(resultSet.getInt("ID"));
                    product.setNameSP(resultSet.getString("NameSP"));
                    product.setPrice(resultSet.getBigDecimal("Price"));
                    product.setDescription(resultSet.getString("Description"));
                    productList.add(product);
                }

                request.setAttribute("productList", productList);

                RequestDispatcher dispatcher = request.getRequestDispatcher("Success.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("Login.jsp");
                PrintWriter out = response.getWriter();
                response.setCharacterEncoding("UTF-8");
                out.println("Login failed. Please check your username and password.");
            }
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.println("An error occurred during login: " + e.getMessage());
        }
    }
}