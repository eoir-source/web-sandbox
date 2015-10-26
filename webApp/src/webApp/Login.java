/*	
 *  Author: David W. Snellings
 * 	Login.java - Servlet class for authenticating the user's credentials.
 *	Reference: http://www.tutorialspoint.com/servlets/servlets-database-access.htm 
 */

package webApp;
 
//Loading required libraries
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.jws.WebService;
import javax.servlet.annotation.WebServlet;

@WebServlet(
        description = "Login", 
        urlPatterns = { "/Login" }
		)

@WebService()
public class Login extends HttpServlet implements AccountRetrieval
{	
	private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	// JDBC driver name and database URL
    	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    	final String user_accounts = "jdbc:mysql://localhost/user_accounts";

    	// Database credentials
    	final String USER = "root";
    	final String PASS = "root";
    	
    	final String accountUSER = request.getParameter("UserName");
    	final String accountPASS = request.getParameter("PassWord");
    	
		// Set response content type
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    String title = accountUSER + "'s Account Information";
	    String docType =
	      "<!doctype html public \"-//w3c//dtd html 4.0 " +
	       "transitional//en\">\n";
	       out.println(docType +
	       "<html>\n" +
	       "<head><title>" + title + "</title></head>\n" +
	       "<body bgcolor=\"#f0f0f0\">\n" +
	       "<h1 align=\"center\">" + title + "</h1>\n");
		
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
    		Connection conn = DriverManager.getConnection(user_accounts, USER, PASS);
    		
    		// Execute SQL query
    		Statement stmt = conn.createStatement();
    		String sql = "SELECT 1 FROM login WHERE account_id = '" + accountUSER + "' AND password = SHA1('" + accountPASS + "')";
    		ResultSet rs = stmt.executeQuery(sql);
    		
    		if(rs.next())
    		{  			  			
    			Statement stmt2 = conn.createStatement();
    			String sql2 = "SELECT * FROM account WHERE account_id = '" + accountUSER + "'";
    			ResultSet rs2 = stmt2.executeQuery(sql2);
    			
    			// Extract data from result set
    			while(rs2.next())
    			{
            		// Retrieve by column name
            		String first_name = rs2.getString("first_name");
            		String last_name = rs2.getString("last_name");
            		String address = rs2.getString("address");
            		String phone = rs2.getString("phone");
            		String checking = rs2.getString("checking");
            		String savings = rs2.getString("savings");
            				
            		// Display values
            		out.println("<h3>Contact Information:</h3>");
            		out.println("<table border=\"2\" style=\"width:100%\"><tr><td><b>First Name</b></td><td>" + first_name + "</td></tr>");
            		out.println("<tr><td><b>Last Name</b></td><td>" + last_name + "</td></tr>");
            		out.println("<tr><td><b>Address</b></td><td>" + address + "</td></tr>");
            		out.println("<tr><td><b>Phone</b></td><td> " + phone + "</td></tr></table>");
            		out.println("<h3>Account Balances:</h3>");
            		out.println("<table border=\"2\" style=\"width:100%\"><tr><td><b>Checking Account</b></td><td>" + checking + "</td></tr>");
            		out.println("<tr><td><b>Savings Account</b></td><td>" + savings + "</td></tr></table>");
    			}
                out.println("</body></html>");
                
                // Clean-up environment
                rs2.close();
                stmt2.close();
    		}
    		else
    		{
    			response.sendRedirect("loginFailure.jsp");
    		}
            
            // Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
    	}
		catch(SQLException se)
		{
			// Handle errors for JDBC
			se.printStackTrace();
		}
    }
    // The “backend” service that retrieves the user's bank account info from a database.
	public Info getInfo(String accountUSER)
	{
		Info accountInfo = new Info();
		// .....
	}
}