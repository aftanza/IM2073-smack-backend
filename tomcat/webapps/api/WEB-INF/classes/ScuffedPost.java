// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;             // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//import javax.servlet.*;             // Tomcat 9
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;

@WebServlet("/help")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class ScuffedPost extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("text/plain");
      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();

      try (
         // Step 1: Allocate a database 'Connection' object
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/smack?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "myuser");   // For MySQL
               // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

         // Step 2: Allocate a 'Statement' object in the Connection
         Statement stmt = conn.createStatement();
      ) {

         String sqlStr = "insert into cart (user_id, listing_id, quantity) values (" + "'" + request.getParameter("user_id") + "'" +  ","+ "'" + request.getParameter("listing_id") + "'" + "," + "'" + request.getParameter("quantity") + "'" + ")";

         // ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server
         int count = stmt.executeUpdate(sqlStr);

      } catch(Exception ex) {
         out.println("Error: " + ex.getMessage());
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)

         out.println("]");

      out.close();
   }

}

