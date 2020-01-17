package Websockets;

import Websockets.ServerStates.WebsocketsCommunicatorService;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.websocket.WebSocketContainer;
import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpointConfig;


public class WebsocketServer {

    private static final int PORT = 8095;
    private static final Logger log = LoggerFactory.getLogger(WebsocketServer.class);

    public static void main(String[] args) {
        startWebSocketServer();
    }

   //  Start the web socket server
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

       try {
           // Initialize javax.websocket layer
           ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(webSocketContext);

           //TODO: Note, that since configureContext was deprecated, I also tried to use the non-deprecated version, .configure -- from https://github.com/eclipse/sprotty-server/blob/master/examples/runner/src/main/java/org/eclipse/sprotty/examples/ExampleLauncher.java
           WebSocketServerContainerInitializer.configure(webSocketContext, (servletContext, serverContainer) -> {
//               serverContainer.addEndpoint(ServerEndpointConfig.Builder
//                       .create(WebsocketsCommunicatorService.class, "/circlegraph")
//                       .configurator(new ExampleEndpointConfigurator(circleGraphInjector))
//                       .build());
               serverContainer.addEndpoint(WebsocketsCommunicatorService.class);
           });

           //TODO: I also tried this
//           final ServerContainer serverContainer = (ServerContainer) webSocketContext
//                   .getAttribute("javax.websocket.server.ServerContainer");

           //serverContainer.addEndpoint(etc)


           // Add WebSocket endpoint to javax.websocket layer
           wscontainer.addEndpoint(WebsocketsCommunicatorService.class);

           webSocketServer.start();
           //server.dump(System.err);

           webSocketServer.join();
       } catch (Throwable t) {
           t.printStackTrace(System.err);
       }
    }



// todo: and this

//    public static void startWebsocketServerTakeTwo() {
//        Server server = new Server("0.0.0.0", 8095, "/", WebsocketsCommunicatorService.class);
//
//        try {
//
//            server.start();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            System.out.print("Please press a key to stop the server.");
//            reader.readLine();
//
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        } finally {
//            server.stop();
//        }
//    }





}
