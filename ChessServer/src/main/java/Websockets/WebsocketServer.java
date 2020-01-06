package Websockets;

import Websockets.ServerStates.WebsocketsCommunicatorService;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;

import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.server.ServerContainer;


public class WebsocketServer {

    private static final int PORT = 8095;
    private static final Logger log = LoggerFactory.getLogger(WebsocketServer.class);

    public static void main(String[] args) {
        startWebSocketServer();
    }

    // Start the web socket server
   public static void startWebSocketServer() {

        Server webSocketServer = new Server();
        ServerConnector connector = new ServerConnector(webSocketServer);
        connector.setPort(PORT);
        webSocketServer.addConnector(connector);

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler webSocketContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        webSocketContext.setContextPath("/");
        webSocketServer.setHandler(webSocketContext);

       final ServerContainer serverContainer = (ServerContainer) webSocketContext
               .getAttribute("javax.websocket.server.ServerContainer");




       try {
            // Initialize javax.websocket layer

            // Add WebSocket endpoint to javax.websocket layer
            serverContainer.addEndpoint(WebsocketsCommunicatorService.class);

            webSocketServer.start();
            //server.dump(System.err);

            webSocketServer.join();
        } catch (Throwable t) {
            t.printStackTrace(System.err);
        }
    }







}
