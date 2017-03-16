package edu.cs313.byui.jdbctutorial;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ele Thompson
 */
@WebServlet(name = "Person", urlPatterns = {"/Person"})
public class Person extends HttpServlet {

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
            Statement childStatement = connection.createStatement();
            Statement ancestors = connection.createStatement();

            // Get the person's id from the URL
            int person_id = Integer.parseInt(request.getParameter("id"));
            // Get the information for the main person
            String SQL = "SELECT id, name, birthday FROM person "
                    + "WHERE id = " + person_id;

            ResultSet resultSet = statement.executeQuery(SQL);

            SQL = "SELECT person.id, person.name, person.birthday, childof.parent_id "
                    + "FROM person "
                    + "INNER JOIN childof "
                    + "ON person.id = childof.child_id "
                    + "WHERE parent_id = " + person_id;

            // Results from the query - TODO: ADJUST QUERY TO GRAB USER DETAILS AND ALL THEIR KIDS
            ResultSet childrenResultSet = childStatement.executeQuery(SQL);

            SQL = "SELECT person.id, person.name, person.birthday "
                    + "FROM person "
                    + "INNER JOIN childof "
                    + "ON person.id = childof.parent_id "
                    + "WHERE childof.child_id = " + person_id;

            ResultSet ancestorsResultSet = ancestors.executeQuery(SQL);

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
                out.println("<h1>Person Page</h1>");

                out.println("<p><a href='Persons'>Persons List</a></p>");

                out.println("<hr>Person: <br/>");
                // Get persons information and print it out
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    Date birthday = resultSet.getDate("birthday");

                    out.println("<p><a href='Person?id=" + id + "'>" + name + " - " + birthday + "</a></p>");
                }

                out.println("<hr>Children: <br/>");
                // Print out a list of the childrens
                while (childrenResultSet.next()) {
                    int id = childrenResultSet.getInt("id");
                    String name = childrenResultSet.getString("name");
                    Date birthday = childrenResultSet.getDate("birthday");

                    out.println("<p><a href='Person?id=" + id + "'>" + name + " - " + birthday + "</a></p>");
                }

                out.println("<hr>Parents: <br/>");
                while (ancestorsResultSet.next()) {
                    int id = ancestorsResultSet.getInt("id");
                    String name = ancestorsResultSet.getString("name");
                    Date birthday = ancestorsResultSet.getDate("birthday");

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
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
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
