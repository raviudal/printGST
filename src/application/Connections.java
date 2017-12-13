package application;

import java.sql.*;

public class Connections {

	public static final Connection b2csDBConncetion(){
		
		 Connection c = null;
	      
	      try {
	    	 /* Class.forName("org.sqlite.JDBC");
		         c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\ravi\\workspace1\\test-printGST\\b2csDatabase.sqlite");
		     */    
		         Class.forName("com.mysql.jdbc.Driver");
		         c = DriverManager.getConnection("jdbc:mysql://localhost:3306/b2csDatabase", "root", "");
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
		
		
		return c;
		
	}
}
