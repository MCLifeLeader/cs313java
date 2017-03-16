package edu.cs313.byui.jdbctutorial;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 0249239
 */
@WebServlet(name = "Persons", urlPatterns = {"/Persons"})
public class Persons extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Load JDBC driver classes
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Persons.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection connection = null;
        try {
            // Create connection getConnection(server location, username, password)
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ancestors", "postgres", "(f22t3)");
            // Start a prepared statement
            Statement statement = connection.createStatement();
            // Results from the query
            ResultSet resultSet = statement.executeQuery("SELECT id, name, birthday FROM person");

            // Print out to the page
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Persons</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>JDBC Tutorial - Persons</h1>");

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Date birthday = resultSet.getDate("birthday");

                    out.println("<p><a href='Person?id=" + id + "'>" + name + " - " + birthday + "</a></p>");
                }

                out.println("</body>");
                out.println("</html>");
            }

        } catch (SQLException ex) {
            PrintWriter out = response.getWriter();
            out.println(ex);
            Logger.getLogger(Persons.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Persons.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
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
