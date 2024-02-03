//import java.util.ArrayList;
//import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = -3727179258077207351L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String jdbcUrl = "jdbc:mysql://localhost:3306/dbtestsqli";
        String dbUser = "root";
        String dbPassword = "123456789@";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM dbtestsqli.users WHERE username = '" + username + "' AND password='" + password + "'";
            System.out.println("Executing 	query: " + sql);

            ResultSet resultSet = stmt.executeQuery(sql);

            if (resultSet.next()) {
                HttpSession session = request.getSession();
               session.setAttribute("username", username);
                System.out.println("Login successful. Redirecting to welcome.jsp");
                response.setCharacterEncoding("UTF-8");
                response.sendRedirect("welcome.jsp");
            } else {
                response.sendRedirect("index.jsp");
                PrintWriter out = response.getWriter();
                response.setCharacterEncoding("UTF-8");
                out.println("Login failed. Please check your username and password.");
           }
            
            
            //dump
//            if (resultSet.next()) {
//                HttpSession session = request.getSession();
//                session.setAttribute("username", username);
//                System.out.println("Login successful. Dumping data to the web page.");
//
//                response.sendRedirect("welcome.jsp");
//                List<String> userDataList = new ArrayList<>();
//
//       
//                do {
//                    String userData = "Username: " + resultSet.getString("username") + ", Password: " + resultSet.getString("password");
//                    userDataList.add(userData);
//
//                } while (resultSet.next());
//
//                session.setAttribute("userDataList", userDataList);
//            } else {
//                response.sendRedirect("index.jsp");
//                PrintWriter out = response.getWriter();
//                response.setCharacterEncoding("UTF-8");
//                out.println("Login failed. Please check your username and password.");
//            }


            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            PrintWriter out = response.getWriter();
            out.println("An error occurred during login: " + e.getMessage());
        }
    }
}

//' UNION SELECT null, username, password FROM dbtestsqli.users WHERE '1'='1'--
//' UNION SELECT null, null, null FROM dbtestsqli.users WHERE username = 'user1' --
//' UNION SELECT null, null, null FROM dbtestsqli.users WHERE table_schema = 'users' -- 
