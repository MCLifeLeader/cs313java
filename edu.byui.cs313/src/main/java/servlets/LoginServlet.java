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
        // Declare correct username and password
        String correctUsername = "userId";
        String correctPassword = "test123";
        
        // Grab the incoming datas
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(username.equals(correctUsername) && password.equals(correctPassword)) {
            // Set the session stuff yasss
            request.getSession().setAttribute("username", username);
            response.sendRedirect("welcome.jsp");
        } else {
            // Something was incorrect
            request.setAttribute("error", "Yo crap is broken");
            // Redirect back to login.jsp
            request.getRequestDispatcher("login.jsp").forward(request,response);
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
