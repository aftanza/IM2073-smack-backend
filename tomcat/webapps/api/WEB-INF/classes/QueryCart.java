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

   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
               throws ServletException, IOException {
      response.setContentType("application/json");
      // response.setContentType("text/html");
      PrintWriter out = response.getWriter();

      try (
         Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/smack?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "myuser");
         Statement stmt = conn.createStatement();
      ) {
         if(request.getParameter("getListingData") != null){
            if(request.getParameter("getListingData").equals("true")){
               // String sqlStr = "SELECT * FROM listings where id IN (SELECT listing_id FROM cart WHERE user_id = " + "'" + request.getParameter("user_id") + "'" + ")";
               String sqlStr = "SELECT listings.id, listings.name, listings.image, listings.brand, listings.description, listings.category, listings.price, cart.listing_id, cart.quantity, cart.user_id FROM listings INNER JOIN cart ON cart.listing_id = listings.id where user_id = " + "'" + request.getParameter("user_id") + "'";
               ResultSet rset = stmt.executeQuery(sqlStr);

               boolean isFirstObject = true;
               out.print("[");
               while(rset.next()) {
                  if(!isFirstObject){
                     out.print(",");
                  }

                  out.print("{");
                  out.print("\"id\":\"" + rset.getString("listing_id") + "\","); 
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
            } 
         }
         else{
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
            out.println("]");
         }      

      } catch(Exception ex) {
         out.println("Error: " + ex.getMessage());
      }  // Step 5: Close conn and stmt - Done automatically by try-with-resources (JDK 7)


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

         if(request.getParameter("deleteAll") != null){
            if(request.getParameter("deleteAll").equals("true")){
               sqlStr = "delete from cart where user_id = " + "'" + request.getParameter("user_id") + "'";
            }
         }
         else{
            sqlStr = "delete from cart where listing_id = " + "'" + request.getParameter("listing_id") + "'" + " and user_id = " + "'" + request.getParameter("user_id") + "'";
         }

         int count = stmt.executeUpdate(sqlStr);  // Send the query to the server
         // out.println(sqlStr);


         String sqlGet = "SELECT * FROM listings where id IN (SELECT listing_id FROM cart WHERE user_id = " + "'" + request.getParameter("user_id") + "'" + ")";

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

}
