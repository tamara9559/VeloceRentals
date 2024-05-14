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

@WebServlet("/category/election1")
public class Election1Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        VehicleService service = new VehicleServiceImpl();
        List<Vehicle> vehicles = service.toList();

        LoginService auth = new LoginServiceSessionImpl();
        Optional<String> usernameOptional = auth.getUsername(req);


        resp.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {

            if (vehicles != null) {
                vehicles.forEach(a -> a.setCategory("carro"));
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("    <head>");
                out.println("        <meta charset=\"UTF-8\">");
                out.println("        <title>Listado de carros</title>");
                out.println("    </head>");
                out.println("    <body>");
                out.println("        <h1>Listado de carros!</h1>");
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

            }
            out.println("    </body>");
            out.println("</html>");
        }
    }
}