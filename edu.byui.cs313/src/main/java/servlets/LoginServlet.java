package servlets;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class LoginServlet 
	extends HttpServlet
{
	private String correctUsername = "michael";
	private String correctPassword = "password";
	
	private static final long serialVersionUID = 1L;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        // Grab the incoming datas
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(validateLogin(username,password))
        {
            request.getRequestDispatcher("ViewPost.jsp").forward(request,response);
        }
        else
        {
            request.getRequestDispatcher("LoginError.jsp").forward(request,response);
        }
    }

    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
    {		
    	response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.print("Login Failure");
		out.flush();
		out.close();
	}
	
    @Override
    public String getServletInfo() 
    {
        return "Simple Login Control";
    }
    
    private boolean validateLogin(String userName, String password) throws IOException
    {
    	boolean isLoginValid = false;
    	
        String contextPath = getServletContext().getRealPath("/");

        String xmlFilePath=contextPath+"\\loginDb.txt";
        
        
        System.out.println("michael|password");
        System.out.println("steve|password");
        System.out.println("bob|password");

        File myfile = new File(xmlFilePath);

        myfile.createNewFile();
        
        
        try 
        {
        	InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("loginDb.txt");        
        	BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;
            while ((line = reader.readLine()) != null) 
            {
            	String [] keyPair = line.split("|");            	
            	if(userName.equals(keyPair[0]) && password.equals(keyPair[1]))
            	{
            		isLoginValid = true;
            		break;
            	}
            }
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    	
    	return isLoginValid;
    }
}
