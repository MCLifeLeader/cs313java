package servlets;


import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class LoginServlet 
	extends HttpServlet
{
	private static final long serialVersionUID = 1L;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(validateLogin(username,password))
        {
            request.getRequestDispatcher("NewPost.jsp").forward(request,response);
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
		
		out.println("Login Failure");
		
		//C:\Code\Eclipse\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\edu.byui.cs313\loginDb.txt 
		//String loginFile = getServletContext().getRealPath("/") + "loginDb.txt";
		//out.println(loginFile);

		//FileOutputStream fileOutput = new FileOutputStream(loginFile);
       
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
    	
        try 
        {
            String loginFile = getServletContext().getRealPath("/") + "loginDb.txt";

            File file = new File(loginFile);
            
        	if (!file.exists() || file.length() < 1 )
        	{
        		FileOutputStream fos = new FileOutputStream(file);
        		file.createNewFile();
        		
        		String users = "michael-password\r\nbob-password\r\nsteve-password\r\n";
        		byte[] bytesArray = users.getBytes();
        		
        		fos.write(bytesArray);
        		fos.flush();
        		fos.close();
       	  	}
        	
            InputStream input = new FileInputStream(loginFile);
        	BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String line;
            while ((line = reader.readLine()) != null)
            {
            	String [] keyPair = line.split("-"); //"michael" "password" 
            	
            	if(userName.equals(keyPair[0]) && password.equals(keyPair[1]))
            	{
            		isLoginValid = true;
            		break;
            	}
            }
            
            reader.close();
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
