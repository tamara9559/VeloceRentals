package co.edu.cue.velocerentals.controllers;

import co.edu.cue.velocerentals.model.Vehicle;
import co.edu.cue.velocerentals.services.LoginService;
import co.edu.cue.velocerentals.services.VehicleService;
import co.edu.cue.velocerentals.services.impl.LoginServiceSessionImpl;
import co.edu.cue.velocerentals.services.impl.VehicleServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

/**
 * This servlet is responsible for displaying a list of vehicles, specifically motorcycles.
 * It retrieves a list of vehicles from the VehicleService and checks if the user is logged in.
 * If the user is logged in, it displays the list of motorcycles with their respective prices and availability.
 * If the user is not logged in, it only displays the list of motorcycles with their names and types.
 *
 * @author  [Your Name]
 * @version 1.0
 * @since    [Date]
 */
public class Election2Servlet extends HttpServlet {

    /**
     * Handles HTTP GET requests. Retrieves a list of vehicles from the VehicleService and checks if the user is logged in.
     * If the user is logged in, it displays the list of motorcycles with their respective prices and availability.
     * If the user is not logged in, it only displays the list of motorcycles with their names and types.
     *
     * @param req  the HTTP request
     * @param resp the HTTP response
     * @throws ServletException if the request for the servlet could not be handled
     * @throws IOException       if an input/output error occurs
     * @see HttpServlet#doGet(HttpServletRequest, HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        VehicleService service = new VehicleServiceImpl();
        List<Vehicle> vehicles = service.toList();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);


        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            if (vehicles != null) {
                vehicles.forEach(a -> a.setCategory("moto"));
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Listado de motos</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Listado de motos!</h1>");
                if (usernameOptional.isPresent()) {
                    out.println("<div style='color: red;'>Hola " + usernameOptional.get() + " Bienvenido!</div>");
                }
                out.println("<table>");
                out.println("<tr>");
                out.println("<th>id</th>");
                out.println("<th>nombre</th>");
                out.println("<th>tipo</th>");
                if (usernameOptional.isPresent()) {
                    out.println("<th>precio</th>");
                    out.println("<th>agregar</th>");
                }
                out.println("</tr>");
                vehicles.forEach(p -> {
                    out.println("<tr>");
                    out.println("<td>" + p.getId() + "</td>");
                    out.println("<td>" + p.getName() + "</td>");
                    out.println("<td>" + p.getType() + "</td>");
                    if (usernameOptional.isPresent()) {
                        out.println("<td>" + p.getPrice() + "</td>");
                        out.println("<td>" + p.getAvailability() + "</td>");
                    }
                    out.println("</tr>");
                });
                out.println("</table>");

            }else{
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Lo sentimos no hay motos registradas!");
            }
            out.println("    </body>");
            out.println("</html>");
        }
    }
}