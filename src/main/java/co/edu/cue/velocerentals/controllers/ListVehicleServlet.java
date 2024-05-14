package co.edu.cue.velocerentals.controllers;

import co.edu.cue.velocerentals.model.Vehicle;
import co.edu.cue.velocerentals.services.LoginService;
import co.edu.cue.velocerentals.services.VehicleService;
import co.edu.cue.velocerentals.services.impl.LoginServiceSessionImpl;
import co.edu.cue.velocerentals.services.impl.VehicleServiceImpl;
import co.edu.cue.velocerentals.services.impl.VehicleServiceJdbcImpl;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

/**
 * Servlet implementation class ListVehicleServlet
 */






@WebServlet("/category/list")
public class ListVehicleServlet extends HttpServlet {

    /**
     * Default constructor.
     */
    public ListVehicleServlet() {
        super();
    }

    @Inject
    private VehicleService service;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        //VehicleService service = new VehicleServiceImpl(); este codigo se ahorra por la inyeccion de dependencia
        List<Vehicle> vehicles = service.toList();

        // Get the LoginService instance
        LoginService auth = new LoginServiceSessionImpl();
        // Get the username from the request
        Optional<String> usernameOptional = auth.getUsername(request);

        // Set the content type and character encoding for the response
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            // Print the HTML document's DOCTYPE declaration
            out.println("<!DOCTYPE html>");
            // Print the HTML document's opening tag
            out.println("<html>");
            // Print the HTML document's head section
            out.println("    <head>");
            // Print the HTML document's meta tag for character encoding
            out.println("        <meta charset=\"UTF-8\">");
            // Print the HTML document's title tag
            out.println("        <title>Listado de Vehiculos</title>");
            // Print the HTML document's head section closing tag
            out.println("    </head>");
            // Print the HTML document's body section opening tag
            out.println("    <body>");
            // Print the HTML document's heading tag
            out.println("        <h1>Listado de Vehiculos!</h1>");
            // If the username is present, print a welcome message
            if (usernameOptional.isPresent()) {
                out.println("<div style='color: red;'>Hola " + usernameOptional.get() + " Bienvenido!</div>");
            }
            // Print the HTML document's table opening tag
            out.println("<table>");
            // Print the HTML document's table row opening tag
            out.println("<tr>");
            // Print the HTML document's table header cells for id, name, and type
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>tipo</th>");
            // If the username is present, print the HTML document's table header cells for price and add
            if (usernameOptional.isPresent()) {
                out.println("<th>precio</th>");
                out.println("<th>agregar</th>");
            }
            // Print the HTML document's table row closing tag
            out.println("</tr>");
            // Iterate over the list of vehicles and print each one as a table row
            vehicles.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getName() + "</td>");
                out.println("<td>" + p.getType() + "</td>");
                // If the username is present, print the HTML document's table data cells for price and availability
                if (usernameOptional.isPresent()) {
                    out.println("<td>" + p.getPrice() + "</td>");
                    out.println("<td>" + p.getAvailability() + "</td>");
                }
                // Print the HTML document's table row closing tag
                out.println("</tr>");
            });
            // Print the HTML document's table closing tag
            out.println("</table>");
            // Print the HTML document's body section closing tag
            out.println("    </body>");
            // Print the HTML document's closing tag
            out.println("</html>");
        }
    }
}
