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

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("Good Bye");
		out.flush();
		out.close();
		
        request.getSession().removeAttribute("username");
        response.sendRedirect("index.jsp");
}
	
    @Override
    public String getServletInfo() 
    {
        return "Simple Logout Control";
    }
}
