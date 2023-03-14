// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;             // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//import javax.servlet.*;             // Tomcat 9
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;

@WebServlet("/users")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class QueryUsers extends HttpServlet {

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

         String sqlStr = "select * from users";

         String sqlStr_add = " where ";
         boolean sqlStr_firstParam = true;
         
         if(request.getParameter("id") != null){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }
            sqlStr_add = sqlStr_add + "id = " + "'" + request.getParameter("id") + "'";
            sqlStr_firstParam = false;
         }
         if(request.getParameter("username") != null){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }
            sqlStr_add = sqlStr_add + "username = " + "'" + request.getParameter("username") + "'";
            sqlStr_firstParam = false;
         }
         if(request.getParameter("password") != null){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }
            sqlStr_add = sqlStr_add + "password = " + "'" + request.getParameter("password") + "'";
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
            out.print("\"username\":\"" + rset.getString("username") + "\","); 
            out.print("\"password\":\"" + rset.getString("password") + "\""); 
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
         String sqlStr = "delete from users where id = " + request.getParameter("id");

         int count = stmt.executeUpdate(sqlStr);  // Send the query to the server

      } catch(Exception ex) {
         out.println("Error: " + ex.getMessage());
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)

      out.close();
   }

   // public void doPut(HttpServletRequest request, HttpServletResponse response)
   //             throws ServletException, IOException {
   //    response.setContentType("application/json");

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
   //       String sqlStr = "update users set quantity = " + request.getParameter("quantity") + " where id = " + request.getParameter("id");

   //       int count = stmt.executeUpdate(sqlStr);  // Send the query to the server

   //    } catch(Exception ex) {
   //       out.println("Error: " + ex.getMessage());
   //    }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)

   //    out.close();
   // }
}

