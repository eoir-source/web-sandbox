/*	Author: David W. Snellings
 * 	LoginServlet.java - Servlet class for authenticating the user's credentials.
 *	Reference: http://www.journaldev.com/1877/java-servlet-tutorial-with-examples-for-beginners 
 */

package login;
 
import java.io.IOException; 
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet; // Provides methods, such as doGet() and doPost(), for handling HTTP-specific services.
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
 
/**
 * Servlet implementation class LoginServlet
 */

@WebServlet(
        description = "Login Servlet", 
        urlPatterns = { "/LoginServlet" }, 
        initParams = { 
                @WebInitParam(name = "UserLogin", value = "David"), 
                @WebInitParam(name = "PassWord", value = "Pa33W0rd")
        })

public class LoginServlet extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
        
    public void init() throws ServletException {
        // We can create DB connection resource here and set it to Servlet context.
        if(getServletContext().getInitParameter("dbURL").equals("jdbc:mysql://localhost/mysql_db") &&
        	 getServletContext().getInitParameter("dbUser").equals("mysql_user") &&
             getServletContext().getInitParameter("dbUserPwd").equals("mysql_pwd"))
        getServletContext().setAttribute("DB_Success", "True");
        else throw new ServletException("DB Connection error");
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
 
        // Get request parameters for UserLogin and PassWord.
        String UserLogin = request.getParameter("UserLogin");
        String PassWord = request.getParameter("PassWord");
         
        // Get servlet configuration init parameters.
        String userID = getServletConfig().getInitParameter("UserLogin");
        String password = getServletConfig().getInitParameter("PassWord");
        
        // Console Logging.
        log("User="+UserLogin+"::password="+PassWord);
        
        if(userID.equals(UserLogin) && password.equals(PassWord))
        {
            response.sendRedirect("LoginSuccess.jsp");
        }
        else
        {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);        
        }      
    }
}