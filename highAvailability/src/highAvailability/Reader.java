package highAvailability;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        description = "Reader Servlet", 
        urlPatterns = { "/Reader" }
        )

public class Reader extends HttpServlet {

	String line = null;
	List<String> lines = new ArrayList<String>();
	
	private static final long serialVersionUID = 1L;
	
    public void init() throws ServletException 
    {
    	// Initialize
    }
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	String fileName = "/WEB-INF/userInfo.txt";
    
    	ServletContext context = getServletContext();
    	
    	InputStream is = context.getResourceAsStream(fileName);
    	if (is != null)
    	{
    		InputStreamReader isr = new InputStreamReader(is);
    		
        	try (BufferedReader br = new BufferedReader(isr)) {
        	    while ((line = br.readLine()) != null) {
        	    	lines.add(line);
        	    }
        	    br.close();
        	    
        		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/post.jsp");
        	    request.setAttribute("name", lines.get(0));
        	    request.setAttribute("address", lines.get(1));
        	    request.setAttribute("phone", lines.get(2));
        	    dispatcher.forward(request, response);
        	}		
    	}  
    }
}