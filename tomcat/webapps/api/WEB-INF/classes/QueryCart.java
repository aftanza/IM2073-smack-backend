// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;             // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//import javax.servlet.*;             // Tomcat 9
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;

@WebServlet("/cart")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class QueryCart extends HttpServlet {

   // The doGet() runs once per HTTP GET request to this servlet.
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      // Set the MIME type for the response message
      response.setContentType("application/json");
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
         // Step 3: Execute a SQL SELECT query
         // String sqlStr = "select * from ebookshoporwhatever where author = "
         //       + "'" + request.getParameter("author") + "'"   // Single-quote SQL string
         //       + " and qty > 0 order by price desc";

         String sqlStr = "select * from cart";

         String sqlStr_add = " where ";
         boolean sqlStr_firstParam = true;
         
         if(request.getParameter("id") != null){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }
            sqlStr_add = sqlStr_add + "id = " + "'" + request.getParameter("id") + "'";
            sqlStr_firstParam = false;
         }
         if(request.getParameter("user_id") != null){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }
            sqlStr_add = sqlStr_add + "user_id = " + "'" + request.getParameter("user_id") + "'";
            sqlStr_firstParam = false;
         }
         if(request.getParameter("listing_id") != null){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }
            sqlStr_add = sqlStr_add + "listing_id = " + "'" + request.getParameter("listing_id") + "'";
            sqlStr_firstParam = false;
         }
         if(request.getParameter("quantity") != null){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }
            sqlStr_add = sqlStr_add + "quantity = " + "'" + request.getParameter("quantity") + "'";
            sqlStr_firstParam = false;
         }
         
         if(!sqlStr_firstParam){
            sqlStr = sqlStr + sqlStr_add;
         }         

         ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server

         boolean isFirstObject = true;

         out.print("[");
         while(rset.next()) {
            if(!isFirstObject){
               out.print(",");
            }

            out.print("{");
            out.print("\"id\":\"" + rset.getString("id") + "\","); 
            out.print("\"user_id\":\"" + rset.getString("user_id") + "\","); 
            out.print("\"listing_id\":\"" + rset.getString("listing_id") + "\","); 
            out.print("\"quantity\":\"" + rset.getString("quantity") + "\""); 
            out.print("}");

            if(isFirstObject){
               isFirstObject = false;
            }
         }


      } catch(Exception ex) {
         out.println("Error: " + ex.getMessage());
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)

         out.println("]");

      out.close();
   }

   public void doDelete(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      response.setContentType("text/html");

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
         // Step 3: Execute a SQL SELECT query
         String sqlStr = "delete from cart where listing_id = " + "'" + request.getParameter("listing_id") + "'" + " and user_id = " + "'" + request.getParameter("user_id") + "'";

         int count = stmt.executeUpdate(sqlStr);  // Send the query to the server

      } catch(Exception ex) {
         out.println("Error: " + ex.getMessage());
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)

      out.close();
   }

   // public void doPost(HttpServletRequest request, HttpServletResponse response)
   //             throws ServletException, IOException {
   //    response.setContentType("text/plain");

   //    // Get a output writer to write the response message into the network socket
   //    PrintWriter out = response.getWriter();
   //    try (
   //       // Step 1: Allocate a database 'Connection' object
   //       Connection conn = DriverManager.getConnection(
   //             "jdbc:mysql://localhost:3306/smack?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
   //             "myuser", "myuser");   // For MySQL
   //             // The format is: "jdbc:mysql://hostname:port/databaseName", "username", "password"

   //       // Step 2: Allocate a 'Statement' object in the Connection
   //       Statement stmt = conn.createStatement();
         
         
         
   //    ) {
   //       // Step 3: Execute a SQL SELECT query
   //       // String sqlStr = "update listings set quantity = quantity - " + request.getParameter("quantity") + " where id = " + request.getParameter("id");
   //       // String sqlStr = "insert into cart values(" + request.getParameter("quantity") + " where listing_id = " + "'" + request.getParameter("listing_id") + "'" + " and user_id = " + "'" + request.getParameter("user_id") + "'"; 
   //       // out.print(sqlStr);

   //       // String sqlStr = "insert into cart (" + count + "," + user_id + "," + listing_id + "," + quantity + ")" + "VALUES(1, "A", 19) ON DUPLICATE KEY UPDATE name="A", age=19"
   //       String sqlStr = "insert into cart (user_id, listing_id, quantity) values (" + "'" + request.getParameter("user_id") + "'" +  ","+ "'" + request.getParameter("listing_id") + "'" + "," + "'" + request.getParameter("quantity") + "'" + ")";

   //       try {
            
   //       } catch (Exception e) { /*report an error*/ }
   //       // insert into cart (count, name, age) VALUES(1, "A", 19) ON DUPLICATE KEY UPDATE name="A", age=19;

   //       int count = stmt.executeUpdate(sqlStr);  // Send the query to the server
   //       out.println(sqlStr);

   //    } catch(Exception ex) {
   //       out.println("Error: " + ex.getMessage());
   //    }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)

   //    out.close();


   // }
}
