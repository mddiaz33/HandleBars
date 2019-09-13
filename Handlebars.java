
import java.sql.*;
import java.util.Scanner;
import java.util.*;

public class Handlebars 
{

   public static void main(String[]args){
   Connection conn = null;
   java.util.Date today = new java.util.Date();
 
   try {
   
   while(true){
   conn = DriverManager.getConnection("jdbc:mysql://cs.neiu.edu:3306/db_Spr19_mddiaz33?serverTimezone=UTC&user=db_Spr19_mddiaz33&password=Neiu2828");   /// Do something with the Connection
   Scanner kbd = new Scanner(System.in);
    
    System.out.println("\n \n \n \n \n \n \n\n\n\n\n  Hi Please enter Your First and Last Name to check in.");
  String fullName = kbd.nextLine();
    
    Statement stmt= conn.createStatement();

     String lastName = fullName.split(" ")[fullName.split(" ").length-1];
     String firstName = fullName.substring(0, fullName.length() - lastName.length());
   
    
       System.out.print("Thanks \n \n ");
       String sql = "SELECT * FROM Membership WHERE guestNo = (SELECT guestNo FROM Guest WHERE firstName = '"+ firstName +"' AND lastName = '"+lastName+"');";
      
       ResultSet rs = stmt.executeQuery(sql);
      
      if(rs.next()){

       String date = rs.getDate("MembershipEnd").toString();
       date = date.replaceAll("-","/");
       int guestNo = rs.getInt("guestNo");

       java.util.Date expires = new java.util.Date(date);
       
       if ( today.compareTo(expires)> 0){
          String price  = rs.getString("price");
   
         System.out.println("Oops, your membership is exired. Renew due:  $"+price );
   sql = "UPDATE Membership SET mStatus = 'N' WHERE guestNo = "+guestNo+";"; 
         
               
         
  stmt.executeUpdate(sql);
      
        System.out.print("Changing status.... to expired" );
         }
       else 
       System.out.println("Welcome to Handle Bars! \n \n \n \n \n \n \n\n\n\n\n ");
       
   }
   else 
   System.out.print("Member not found, please purchase a day pass for $2!\n \n \n \n \n \n \n\n\n\n\n  ");
   
   }
             


   } 
   
   
   
   catch (SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
      }
    }
   }
   
   
