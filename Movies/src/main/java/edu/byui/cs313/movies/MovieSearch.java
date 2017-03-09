package edu.byui.cs313.movies;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vmthompson
 */
@WebServlet(name = "MovieSearch", urlPatterns = {"/MovieSearch"})
public class MovieSearch extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get string from the form
        String search = request.getParameter("search");
        
        // Get rid of yo spaces
        search = search.replace(" ", "+");
        
        // Create URL
        URL url = new URL("http://www.omdbapi.com/?s=" + search);
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = mapper.readValue(url, Map.class);
        List list = (List)map.get("Search");

        for (Object item : list) {
            Map<String, Object> innerMap = (Map<String, Object>)item;

            //for (String key : innerMap.keySet()) {
                //System.out.println(key + ": " + innerMap.get(key));
            //}
        }
        
        request.setAttribute("movies", list);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
