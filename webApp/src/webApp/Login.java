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
import javax.servlet.annotation.WebServlet;

@WebServlet(
        description = "Login", 
        urlPatterns = { "/Login" }
		)

public class Login extends HttpServlet
{	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{	
		// JDBC driver name and database URL
		final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
		final String DB_URL="jdbc:mysql://localhost/aadt_ied";

		//  Database credentials
		final String USER = request.getParameter("UserName");
		final String PASS = request.getParameter("PassWord");
		
		try
		{
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// Open a connection
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
					
			if(conn != null)
			{
				response.sendRedirect("loginSuccess.jsp");
			}
			
			conn.close();
		}
		catch(Exception e)
		{
			log("Connection Failed: " + DB_URL);
			e.printStackTrace();
		}
	}
}