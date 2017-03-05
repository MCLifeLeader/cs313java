package servlets;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewPost
 */
@WebServlet(name = "ViewPost", urlPatterns = {"/ViewPost"})
public class ViewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewPostServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	List<String> tList = new ArrayList<String>();
    	List<String> postList = new ArrayList<String>();
    	
        String commentFile = getServletContext().getRealPath("/") + "comments.txt";
        
        InputStream input = new FileInputStream(commentFile);
    	BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String line;
        while ((line = reader.readLine()) != null)
        {
        	tList.add(line);
        }
        
        for(int ii=tList.size()-1; ii>=0; ii--)
        {
        	postList.add(tList.get(ii));
        }
        
        reader.close();
        
        renderHtml(postList, response);
    }
    
    private void renderHtml( List<String> postList, HttpServletResponse response ) throws IOException
    {
    	response.setContentType("text/html");		
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
          out.println("<head>");
            out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            out.println("<title>View Posts</title>");
          out.println("</head>");
          out.println("<body>");
            out.println("<h2>View Posts</h2>");
              out.println("<table>");

              for(int i = 0; i < postList.size(); i++ ) {
                out.println("<tr>");      
                out.println("<td>" + postList.get(i) + "</td>");
                out.println("</tr>"); 
              }

            out.println("</table>");
          out.println("</body>");
        out.println("</html>");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request, response);
	}

}
      