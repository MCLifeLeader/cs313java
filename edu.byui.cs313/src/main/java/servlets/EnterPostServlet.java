package servlets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EnterPost
 */
@WebServlet(name = "EnterPost", urlPatterns = {"/EnterPost"})
public class EnterPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnterPostServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        request.getRequestDispatcher("NewPost.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
        String comment = request.getParameter("comment").trim().replace('\r', ' ').replace("\n","<br/>") + "\r\n";
		
        String commentFile = getServletContext().getRealPath("/") + "comments.txt";
        
        File file = new File(commentFile);
        
        // create my file for the first time. First User Post
    	if (file.length() < 1)
    	{
    		FileOutputStream fos = new FileOutputStream(file);
    		file.createNewFile();    		    		
    		byte[] bytesArray = comment.getBytes();    		
    		fos.write(bytesArray);
    		fos.flush();
    		fos.close();
   	  	}
    	else
    	{
		  try 
		  {
		      BufferedWriter out = new BufferedWriter(new FileWriter(commentFile, true));
		      
		      out.write(comment);
		      out.close();
		  }
		  catch (IOException e) 
		  {
		      System.out.println("exception occoured"+ e);
		  }
		}

    	request.getRequestDispatcher("ViewPost").forward(request,response);
	}

    @Override
    public String getServletInfo() 
    {
        return "Enter Post Comments";
    }
}
