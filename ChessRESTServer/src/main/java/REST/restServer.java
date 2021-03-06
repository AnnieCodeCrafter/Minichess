package REST;



import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;



public class restServer {

    public static void main(String[] args) throws Exception {
        startRest();

    }

    public static void startRest() throws Exception {
        ServletContextHandler context = new
                ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        Server jettyServer = new Server(8098);
        jettyServer.setHandler(context);
        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
                RestEndpoints.class.getCanonicalName());

        try {
            jettyServer.start();
            jettyServer.join();

        }
        catch(Exception ex)
        {
            String temp = "";
            jettyServer.stop();
            jettyServer.destroy();
        }
//        finally {
//            jettyServer.destroy();
//        }
    }
}
