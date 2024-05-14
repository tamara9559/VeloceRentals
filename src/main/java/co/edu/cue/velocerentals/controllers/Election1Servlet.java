package co.edu.cue.velocerentals.controllers;

import co.edu.cue.velocerentals.model.Vehicle;
import co.edu.cue.velocerentals.services.LoginService;
import co.edu.cue.velocerentals.services.VehicleService;
import co.edu.cue.velocerentals.services.impl.LoginServiceSessionImpl;
import co.edu.cue.velocerentals.services.impl.VehicleServiceImpl;
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
 * Servlet implementation class Election1Servlet
 */
@WebServlet({"/election1","/election1.html"})
public class Election1Servlet extends HttpServlet {

    /**
     * Default constructor.
     */
    public Election1Servlet() {
        super();
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Instantiate the vehicle service
        VehicleService service = new VehicleServiceImpl();
        // Get the list of vehicles
        List<Vehicle> vehicles = service.toList();

        // Instantiate the login service
        LoginService auth = new LoginServiceSessionImpl();
        // Get the username from the request
        Optional<String> usernameOptional = auth.getUsername(request);

        // Set the content type and character encoding for the response
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            // If the list of vehicles is not null
            if (vehicles != null) {
                // Iterate through the list of vehicles and set the category to "carro"
                vehicles.forEach(a -> a.setCategory("carro"));
                // Print the HTML document's DOCTYPE declaration
                out.println("<!DOCTYPE html>");
                // Print the HTML document's opening tag
                out.println("<html>");
                // Print the HTML document's head section
                out.println("    <head>");
                // Set the character encoding for the HTML document
                out.println("        <meta charset=\"UTF-8\">");
                // Set the title of the HTML document
                out.println("        <title>Listado de carros</title>");
                // Print the HTML document's head section's closing tag
                out.println("    </head>");
                // Print the HTML document's body section's opening tag
                out.println("    <body>");
                // Print the heading "Listado de carros!"
                out.println("        <h1>Listado de carros!</h1>");
                // If the username is present
                if (usernameOptional.isPresent()) {
                    // Print a welcome message for the user
                    out.println("<div style='color: red;'>Hola " + usernameOptional.get() + " Bienvenido!</div>");
                }
                // Print the opening tag of the table
                out.println("<table>");
                // Print the table's header row
                out.println("<tr>");
                // Print the table's header columns
                out.println("<th>id</th>");
                out.println("<th>nombre</th>");
                out.println("<th>tipo</th>");
                // If the username is present
                if (usernameOptional.isPresent()) {
                    // Print the table's header columns for price and add button
                    out.println("<th>precio</th>");
                    out.println("<th>agregar</th>");
                }
                // Print the table's header row's closing tag
                out.println("</tr>");
                // Iterate through the list of vehicles and print each row of the table
                vehicles.forEach(p -> {
                    out.println("<tr>");
                    // Print the table's data columns for each vehicle
                    out.println("<td>" + p.getId() + "</td>");
                    out.println("<td>" + p.getName() + "</td>");
                    out.println("<td>" + p.getType() + "</td>");
                    // If the username is present
                    if (usernameOptional.isPresent()) {
                        // Print the table's data columns for price and availability
                        out.println("<td>" + p.getPrice() + "</td>");
                        out.println("<td>" + p.getAvailability() + "</td>");
                    }
                    // Print the table's row's closing tag
                    out.println("</tr>");
                });
                // Print the table's closing tag
                out.println("</table>");

            } else {
                // If the list of vehicles is null, send a 404 error response
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Lo sentimos no hay carros registrados!");
            }
            // Print the HTML document's body section's closing tag
            out.println("    </body>");
            // Print the HTML document's closing tag
            out.println("</html>");
        }
    }
}