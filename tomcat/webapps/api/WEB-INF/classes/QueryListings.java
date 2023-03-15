// To save as "<TOMCAT_HOME>\webapps\hello\WEB-INF\classes\QueryServlet.java".
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;             // Tomcat 10
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
//import javax.servlet.*;             // Tomcat 9
//import javax.servlet.http.*;
//import javax.servlet.annotation.*;

@WebServlet("/listings")   // Configure the request URL for this servlet (Tomcat 7/Servlet 3.0 upwards)
public class QueryListings extends HttpServlet {

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
         // Step 3: Execute a SQL SELECT query
         // String sqlStr = "select * from ebookshoporwhatever where author = "
         //       + "'" + request.getParameter("author") + "'"   // Single-quote SQL string
         //       + " and qty > 0 order by price desc";

         String sqlStr = "select * from listings";

         String sqlStr_add = "";
         String sqlStr_order = "";
         boolean sqlStr_firstParam = true;
         boolean iter_first = true;
         
         if(request.getParameter("id") != null){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }
            sqlStr_add = sqlStr_add + "id = " + "'" + request.getParameter("id") + "'";
            sqlStr_firstParam = false;
         }
         if(request.getParameter("name") != null){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }
            sqlStr_add = sqlStr_add + "name = " + "'" + request.getParameter("name") + "'";
            sqlStr_firstParam = false;
         }
         if(request.getParameter("brand") != null){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }
            sqlStr_add = sqlStr_add + "brand = " + "'" + request.getParameter("brand") + "'";
            sqlStr_firstParam = false;
         }
         if(request.getParameter("price") != null && !request.getParameter("price").isEmpty()){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }
            sqlStr_add = sqlStr_add + "price = " + "'" + request.getParameter("price") + "'";
            sqlStr_firstParam = false;
         }

         if(request.getParameter("category") != null && !request.getParameter("category").isEmpty()){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }

            // format: ?category=driedFruit+chips+crunchies+seeds+pretzel

            String[] ranges = request.getParameter("category").split(" ");
            // for(String i : ranges){
            //    out.print(i + " ");
            // }

            iter_first = true;
            for (String i : ranges){
               if(!sqlStr_firstParam){
                  if(!iter_first){
                     sqlStr_add = sqlStr_add + " or ";
                  }
               }
               if(iter_first) {
                  sqlStr_add = sqlStr_add + " ( ";
               }

               if(i.equals("driedFruit")){
                  sqlStr_add = sqlStr_add + "category = " + "'" + "dried fruit" + "'";
               }
               else if (i.equals("chips")) {
                  sqlStr_add = sqlStr_add + "category = " + "'" + "chips" + "'";
               }
               else if (i.equals("crunchies")){
                  sqlStr_add = sqlStr_add + "category = " + "'" + "crunchies" + "'";
               }
               else if (i.equals("seeds")){
                  sqlStr_add = sqlStr_add + "category = " + "'" + "seeds" + "'";
               }
               else if (i.equals("pretzel")){
                  sqlStr_add = sqlStr_add + "category = " + "'" + "pretzel" + "'";
               }

               iter_first = false;
               sqlStr_firstParam = false;
            }
            sqlStr_add = sqlStr_add + " ) ";        
         }


         if(request.getParameter("description") != null){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }
            sqlStr_add = sqlStr_add + "description = " + "'" + request.getParameter("description") + "'";
            sqlStr_firstParam = false;
         }
         if(request.getParameter("image") != null){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }
            sqlStr_add = sqlStr_add + "image = " + "'" + request.getParameter("image") + "'";
            sqlStr_firstParam = false;
         }
         if(request.getParameter("quantity") != null){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }
            sqlStr_add = sqlStr_add + "quantity = " + request.getParameter("quantity");
            sqlStr_firstParam = false;
         }
         if(request.getParameter("priceRange") != null && !request.getParameter("priceRange").isEmpty()){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " and ";
            }

            // format: ?priceRange=toFive+fiveToTen+tenToFifteen+moreThanFifteen

            String[] ranges = request.getParameter("priceRange").split(" ");
            // for(String i : ranges){
            //    out.print(i + " ");
            // }

           
            iter_first = true;
            for (String i : ranges){
               if(!sqlStr_firstParam){
                  if(!iter_first){
                     sqlStr_add = sqlStr_add + " or ";
                  }
               }
               if(iter_first) {
                  sqlStr_add = sqlStr_add + " ( ";
               }  


               if(i.equals("toFive")){
                  sqlStr_add = sqlStr_add + "( price >= 0 and price <= 5 )";
               }
               else if (i.equals("fiveToTen")) {
                  sqlStr_add = sqlStr_add + "( price >= 5 and price <= 10 )";
               }
               else if (i.equals("tenToFifteen")){
                  sqlStr_add = sqlStr_add + "( price >= 10 and price <= 15 )";
               }
               else if (i.equals("moreThanFifteen")){
                  sqlStr_add = sqlStr_add + "( price >= 15 )";
               }
               iter_first = false;
               sqlStr_firstParam = false;
            }     
            sqlStr_add = sqlStr_add + " ) ";   
         }

         if(request.getParameter("sort") != null && !request.getParameter("sort").isEmpty()){
            if(!sqlStr_firstParam){
               sqlStr_add = sqlStr_add + " ";
            }
            //format: asc/desc
            sqlStr_order = "order by price " + request.getParameter("sort");
            sqlStr_firstParam = false;
         }
         
         if(!sqlStr_firstParam){
            if(sqlStr_add.length() != 0){
               sqlStr = sqlStr + " where " + sqlStr_add + sqlStr_order;
            }
            else{
               sqlStr = sqlStr + " " + sqlStr_order;
            }
         }         

         ResultSet rset = stmt.executeQuery(sqlStr);  // Send the query to the server
         // out.println(sqlStr);
         // out.println("Statement: " + sqlStr);


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
         out.println("Error: " + ex.getMessage() + "\n");
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)

         

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
         String sqlStr = "delete from listings where id = " + request.getParameter("id");

         int count = stmt.executeUpdate(sqlStr);  // Send the query to the server

      } catch(Exception ex) {
         out.println("Error: " + ex.getMessage());
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)

      out.close();
   }

   public void doPut(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
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
         // String sqlStr = "update listings set quantity = quantity - " + request.getParameter("quantity") + " where id = " + request.getParameter("id");
         String sqlStr = "update listings set quantity = " + request.getParameter("quantity") + " where id = " + request.getParameter("id");

         int count = stmt.executeUpdate(sqlStr);  // Send the query to the server

      } catch(Exception ex) {
         out.println("Error: " + ex.getMessage() + "\n");
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)

      out.close();
   }
}
