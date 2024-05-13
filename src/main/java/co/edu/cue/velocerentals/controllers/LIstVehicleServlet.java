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
import java.sql.Connection;
import java.util.List;
import java.util.Optional;

@WebServlet("/category")
public class LIstVehicleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection conn = (Connection) req.getAttribute("conn");
        VehicleService service = new VehicleServiceImpl(conn);
        List<Vehicle> vehicles = service.toList();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);

        String mensajeRequest = (String) req.getAttribute("mensaje");
        String mensajeApp = (String) getServletContext().getAttribute("mensaje");
        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Listado de Vehiculos</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Listado de Vehiculos!</h1>");
            if(usernameOptional.isPresent()) {
                out.println("<div style='color: red;'>Hola " + usernameOptional.get() + " Bienvenido!</div>");
            }
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>nombre</th>");
            out.println("<th>tipo</th>");
            if(usernameOptional.isPresent()) {
                out.println("<th>precio</th>");
                out.println("<th>agregar</th>");
            }
            out.println("</tr>");
            vehicles.forEach(p -> {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getName() + "</td>");
                out.println("<td>" + p.getType() + "</td>");
                if(usernameOptional.isPresent()) {
                    out.println("<td>" + p.getPrice() + "</td>");
                    out.println("<td>"  + p.getAvailability() + "</td>");
                }
                out.println("</tr>");
            });
            out.println("</table>");
            out.println("<p>" + mensajeApp + "</p>");
            out.println("<p>" + mensajeRequest + "</p>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

}
