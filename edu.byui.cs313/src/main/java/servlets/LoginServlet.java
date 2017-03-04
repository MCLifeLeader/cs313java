package servlets;


import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class LoginServlet 
	extends HttpServlet
{
	private String testUsername = "michael";
	private String testPassword = "password";
	
	private static final long serialVersionUID = 2L;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");

        if (username != null && 
        		password != null &&
        		username.equals(testUsername) && 
                password.equals(testPassword)) 
        {

            request.getSession().setAttribute("username", username);            
            response.sendRedirect("Welcome.jsp");
        } 
        else 
        {
        	request.setAttribute("error", "Invalid login");
        	request.getRequestDispatcher("Login.jsp").forward(request,response);
        }
    }

    @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
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
}
