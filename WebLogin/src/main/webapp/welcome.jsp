<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.io.PrintWriter" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
	<% 
            List<String> userDataList = (List<String>) session.getAttribute("userDataList");

            if (userDataList != null && !userDataList.isEmpty()) {
                for (String userData : userDataList) {
                    out.println("<p>" + userData + "</p>");
                }
            } else {
                out.println("<p>No user data available.</p>");
            }
        %>
 

    <div align="center">
        <form action="<%= request.getRequestURI() %>" method="post">
            <p>name -> database</p>
            First name : <input type="text" name="firstname">
            <input type="submit" name="submit" value="Submit">
        </form>

        <% 
            try {
                String servername = "localhost:3306";
                String username = "root";
                String password = "123456789@";
                String db = "dbtestsqli";

                Connection conn = DriverManager.getConnection("jdbc:mysql://" + servername + "/" + db, username, password);
                Statement stmt = conn.createStatement();

                if (request.getMethod().equals("POST")) {
                    String firstname = request.getParameter("firstname");
                    String sql = "SELECT lastname FROM nameuser WHERE firstname='" + firstname + "'";
                    ResultSet result = stmt.executeQuery(sql);

                    out.println("<br>");

                    while (result.next()) {
                        out.println("Last name: " + result.getString("lastname"));
                    }
                    
                    conn.close();
                    out.println("0 results");
                }

                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                // out.println("<p>An error occurred: " + e.getMessage() + "</p>");
            }
        %>
    </div>
</body>
</html>
