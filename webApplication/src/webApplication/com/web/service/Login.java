/*	
 *  Author: David W. Snellings
 */

package webApplication.com.web.service;

//Loading required libraries
import javax.jws.WebMethod;
import javax.jws.WebService;
import webApplication.com.web.service.DbManager;

@WebService
public class Login
{	  
	// Declarations & Initializations
	DbManager manager;
	
	public Login()
	{
		manager = new DbManager();
	}
	
	@WebMethod(operationName = "authenticate")
	public int authenticate(String user, String pass)
	{
		System.out.println("I'm in!!");
		int LogIn = manager.login(user, pass);
		
		if(LogIn > 0)
		{
			System.out.print("Login successful");
			return 1;
		}
		else
		{
			System.out.print("Incorrect login details");
			return 0;
		}
	}
	
    public static void main (String[] args)
    {  	    
    	new Login();
/*    	
    	int num = user.authenticate("1337", "password");
    	if (num > 0)
    	{
    		System.out.print("Login successful");
    	}
    	else
    	{  	
    		System.out.print("Incorrect login details");
    	}
*/
    }
}