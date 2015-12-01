/*
 * Author: David W. Snellings
 */

package test;

import java.sql.*;
import javax.sql.*;
import javax.jws.*;
import javax.naming.*;

@WebService
public class TestWebService {
	
		@WebMethod
		public void getInfo(String user)
		{
			Context ctx = null;
			try 
			{
				ctx = new InitialContext();
			} 
			catch (NamingException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			DataSource ds = null;
			try 
			{
				ds = (DataSource)ctx.lookup("java:/MySQL");
			} 
			catch (NamingException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Connection con = null;
			try 
			{
				con = ds.getConnection();
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String sql = "SELECT * FROM account WHERE account_id = '" + user + "'";
			
			PreparedStatement pstmt = null;
			try 
			{
				pstmt = con.prepareStatement(sql);
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try
			{
				// Execute SQL query
				ResultSet rs = pstmt.executeQuery();
			
				while(rs.next())
				{
					// Retrieve by column name
					String first_name = rs.getString("first_name");
					String last_name = rs.getString("last_name");
					String address = rs.getString("address");
					String phone = rs.getString("phone");
					String checking = rs.getString("checking");
					String savings = rs.getString("savings");
					
					System.out.println(first_name);
					System.out.println(last_name);
					System.out.println(address);
					System.out.println(phone);
					System.out.println(checking);
					System.out.println(savings);
				}
			
				// Clean-up environment
				rs.close();
				pstmt.close();
				con.close();	
			}
			catch (SQLException se)
			{
				// Handle errors for JDBC
				se.printStackTrace();
			}
		}
}
