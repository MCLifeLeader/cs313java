package servlets;


import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


@WebServlet(name = "Logout", urlPatterns = {"/Logout"})
public class LogoutServlet 
	extends HttpServlet
{
	
	private static final long serialVersionUID = 3L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Delete the Session variable
        request.getSession().setAttribute("username", null);
        // Redirect to login.jsp page
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
	
    @Override
    public String getServletInfo() 
    {
        return "Simple Logout Control";
    }
}
