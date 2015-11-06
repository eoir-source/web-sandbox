package webApplication.com.web.service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DbManager {
	
	// Declarations & Initializations
	ResultSet rs = null;
	Statement stmt = null;
	Connection con = null;
	
	public DbManager()
	{
		connect();
	}
	
	public void connect()
	{
	    // JDBC driver name and database URL
	    final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	    final String user_accounts = "jdbc:mysql://localhost/user_accounts";

	    // Database credentials
	    final String USER = "root";
	    final String PASS = "root";
	    
	    try
	    {
	    	// Register JDBC driver
	    	Class.forName(JDBC_DRIVER);
	    }
	    catch(Exception e)
	    {
	    	// Handle errors for Class.forName
	    	e.printStackTrace();
	    }
	    	
	    try
	    {
	    	// Open a connection
	    	con = DriverManager.getConnection(user_accounts, USER, PASS);
	    	
	    	stmt = con.createStatement();
	    	
	    	if (!con.isClosed())
	    	{
	    		System.out.println("Successfully connected to MySQL server using TCP/IP.");
	    	}
	    }     	
		catch (Exception e)
		{
			System.err.println("Exception: " + e.getMessage());
		}
	}

	public int login(String user, String pass)
	{
		int success = 0;
		
		// Execute SQL query
		String sql = "SELECT 1 FROM login WHERE account_id = '" + user + "' AND password = SHA1('" + pass + "')";
		try
		{
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				success++;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		System.out.println(success);
		return success;
	}
}
