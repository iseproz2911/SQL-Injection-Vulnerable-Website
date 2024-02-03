import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductDetailServlet", urlPatterns = {"/ProductDetail"})
public class ProductDetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId = request.getParameter("id");
        
        String[] filter = {"SELECT"};
        for (String banned : filter) {
            if (productId.toUpperCase().contains(banned)) {
                System.out.println("productId: " + productId);
                System.out.println("productId toUpperCase: " + productId.toUpperCase());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().println("Access Forbidden");
                return;
            }	
        }
//SQLi!!!
        String sql = "SELECT * FROM dbblindsql.Product WHERE ID = '" + productId + "'";
//      String sql = "SELECT * FROM dbblindsql.Product WHERE ID = ?";
//        PreparedStatement pstmt = conn.prepareStatement(sql)) {
//        pstmt.setInt(1, Integer.parseInt(productId));
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbblindsql", "root", "123456789@");
             Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);
            if (resultSet.next()) {
                String productID = resultSet.getString("ID");
                String name = resultSet.getString("NameSP");
                String price = resultSet.getString("Price");
                String description = resultSet.getString("Description");

                request.setAttribute("productID", productID);
                request.setAttribute("name", name);
                request.setAttribute("price", price);
                request.setAttribute("description", description);

                RequestDispatcher dispatcher = request.getRequestDispatcher("ProductDetail.jsp");
                dispatcher.forward(request, response);
            } else {
                response.getWriter().println("ID not found!!!");
            }

        } catch (SQLException | ServletException | IOException e) {		
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Internal Server Error: " + e.getMessage());
        }
    }
}