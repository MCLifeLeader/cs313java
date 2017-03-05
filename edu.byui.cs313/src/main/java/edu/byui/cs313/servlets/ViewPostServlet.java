package edu.byui.cs313.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

import edu.byui.cs313.models.UserComment;

/**
 * Servlet implementation class ViewPost
 */
@WebServlet(name = "ViewPost", urlPatterns = {"/ViewPost"})
public class ViewPostServlet extends HttpServlet 
{
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
    	List<UserComment> tList = new ArrayList<UserComment>();
    	List<UserComment> postList = new ArrayList<UserComment>();
    	
        String commentFile = getServletContext().getRealPath("/") + "comments.txt";
        
        File file = new File(commentFile);
        if (!file.exists()) 
        {
	    	FileOutputStream fos = new FileOutputStream(file);
	        file.createNewFile();
	        fos.flush();
	        fos.close();
        }
        
        InputStream input = new FileInputStream(commentFile);
    	BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;
        while ((line = reader.readLine()) != null)
        {
        	String[] commentInfo = line.split("\\|\\|");
        	UserComment comment = new UserComment(commentInfo[0], commentInfo[1], commentInfo[2]);
            tList.add(comment);
        }

        for(int ii=tList.size()-1; ii>=0; ii--)
        {
            postList.add(tList.get(ii));
        }
        
        reader.close();
            
        renderHtml(postList, response);
    }

    private void renderHtml(List<UserComment> postList, HttpServletResponse response) throws IOException
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
                
                if (postList.size() > 0) 
                {
                    out.println("<table>");
                        out.println("<tr>");
                            out.println("<th>User</th>");
                            out.println("<th>Date</th>");
                            out.println("<th>Comment</th>");
                        out.println("</tr>"); 

                        for(int i = 0; i < postList.size(); i++ ) 
                        {
                        	UserComment comment = postList.get(i);
                            out.println("<tr>");
                                out.println("<td>" + comment.user + "</td>");
                                out.println("<td>" + comment.date + "</td>");
                                out.println("<td>" + comment.comment + "</td>");
                            out.println("</tr>"); 
                        }
                    out.println("</table>");
                } 
                else 
                {
                    out.println("<h3>No posts, please enter a new post</h3>");
                }

                out.println("<a href='EnterPost'>New Post</a>");
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
