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
      // response.setContentType("text/plain");
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

         String sqlGet = "SELECT * FROM listings where id IN (SELECT listing_id FROM cart WHERE user_id = " + "'" + request.getParameter("user_id") + "'" + ")";
         String sqlStr = "insert into cart (user_id, listing_id, quantity) values (" + "'" + request.getParameter("user_id") + "'" +  ","+ "'" + request.getParameter("listing_id") + "'" + "," + "'" + request.getParameter("quantity") + "'" + ") on duplicate key update quantity = " + request.getParameter("quantity");
         // out.println(sqlStr);      
         // out.println(sqlGet);

         int count = stmt.executeUpdate(sqlStr);
         ResultSet rset = stmt.executeQuery(sqlGet);  

         boolean isFirstObject = true;
         out.print("[");
         while(rset.next()) {
            if(!isFirstObject){
               out.print(",");
            }

            out.print("{");
            out.print("\"id\":\"" + rset.getString("id") + "\","); 
            out.print("\"name\":\"" + rset.getString("name") + "\","); 
            out.print("\"brand\":\"" + rset.getString("brand") + "\","); 
            out.print("\"price\":\"" + rset.getString("price") + "\",");
            out.print("\"category\":\"" + rset.getString("category") + "\","); 
            out.print("\"description\":\"" + rset.getString("description") + "\",");
            out.print("\"image\":\"" + rset.getString("image") + "\",");   
            out.print("\"quantity\":\"" + rset.getString("quantity") + "\""); 
            out.print("}");

            if(isFirstObject){
               isFirstObject = false;
            }
         }
         out.println("]");

      } catch(Exception ex) {
         out.println("Error: " + ex.getMessage());
      } 

      out.close();
   }

   public void doDelete(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      response.setContentType("text/html");
      // response.setContentType("application/json");

      // Get a output writer to write the response message into the network socket
      PrintWriter out = response.getWriter();

      try (

         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/smack?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "myuser");   // For MySQL

         Statement stmt = conn.createStatement();
      ) {
         String sqlStr = "";
         String sqlStr2 = "";
         String sqlStr3 = "";

         sqlStr = "delete from cart where listing_id = " + "'" + request.getParameter("listing_id") + "'" + " and user_id = " + "'" + request.getParameter("user_id") + "'";
         sqlStr2 = "update listings set quantity = quantity - " + request.getParameter("quantity") + " where id = " + "'" + request.getParameter("listing_id") + "'";
         // sqlStr3 = "SELECT * FROM listings where id IN (SELECT listing_id FROM cart WHERE user_id = " + "'" + request.getParameter("user_id") + "'" + ")";
         

         int count = stmt.executeUpdate(sqlStr); 
         int count2 = stmt.executeUpdate(sqlStr2);
         // ResultSet rset = stmt.executeQuery(sqlStr3);
         // out.println(sqlStr);


         // boolean isFirstObject = true;
         // out.print("[");
         // while(rset.next()) {
         //    if(!isFirstObject){
         //       out.print(",");
         //    }

         //    out.print("{");
         //    out.print("\"id\":\"" + rset.getString("id") + "\","); 
         //    out.print("\"name\":\"" + rset.getString("name") + "\","); 
         //    out.print("\"brand\":\"" + rset.getString("brand") + "\","); 
         //    out.print("\"price\":\"" + rset.getString("price") + "\",");
         //    out.print("\"category\":\"" + rset.getString("category") + "\","); 
         //    out.print("\"description\":\"" + rset.getString("description") + "\",");
         //    out.print("\"image\":\"" + rset.getString("image") + "\",");   
         //    out.print("\"quantity\":\"" + rset.getString("quantity") + "\""); 
         //    out.print("}");

         //    if(isFirstObject){
         //       isFirstObject = false;
         //    }
         // }
         // out.println("]");

      } catch(Exception ex) {
         out.println("Error: " + ex.getMessage());
      }  

      out.close();
   }

}

