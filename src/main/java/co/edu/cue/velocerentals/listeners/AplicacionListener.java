package co.edu.cue.velocerentals.listeners;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionListener;

/**
 * AplicacionListener is a Servlet listener that listens for context initialization and destruction events.
 * It logs messages when the application is initialized and destroyed.
 *
 * @author [Your Name]
 * @version 1.0
 */
@WebListener
public class AplicacionListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {

    /**
     * The ServletContext object that represents the servlet context.
     */
    private ServletContext servletContext;

    /**
     * Called when the servlet context is initialized.
     *
     * @param sce the ServletContextEvent object that contains the servlet context and other
     *             pieces of information about the event.
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Logs a message indicating that the application is being initialized.
        sce.getServletContext().log("inicializando la aplicacion!");

        // Stores the ServletContext object for later use.
        servletContext = sce.getServletContext();

        // Sets an attribute in the servlet context with a message.
        servletContext.setAttribute("mensaje", "mensaje enviado desde el listener");
    }

    /**
     * Called when the servlet context is destroyed.
     *
     * @param sce the ServletContextEvent object that contains the servlet context and other
     *             pieces of information about the event.
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Logs a message indicating that the application is being destroyed.
        servletContext.log("destruyendo la aplicacion!");
    }
}
