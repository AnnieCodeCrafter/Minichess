package Websockets;

import Websockets.ServerStates.WebsocketsCommunicatorService;






import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.glassfish.tyrus.server.Server;


public class WebsocketServer {

    private static final int PORT = 8095;
    private static final Logger log = LoggerFactory.getLogger(WebsocketServer.class);

    public static void main(String[] args) {
        startWebsocketServerTakeTwo();
    }

    // Start the web socket server
//   public static void startWebSocketServer() {
//
//        Server webSocketServer = new Server();
//        ServerConnector connector = new ServerConnector(webSocketServer);
//        connector.setPort(PORT);
//        webSocketServer.addConnector(connector);
//
//       try {
//           // Setup the basic application "context" for this application at "/"
//           // This is also known as the handler tree (in jetty speak)
//           ServletContextHandler webSocketContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
//           webSocketContext.setContextPath("/");
//           webSocketServer.setHandler(webSocketContext);
//
////           WebSocketServerContainerInitializer.configure(webSocketContext, (servletContext, serverContainer) -> {
////               serverContainer.addEndpoint(WebsocketsCommunicatorService.class);
////           });
//
//
//            webSocketServer.start();
//
//            webSocketServer.join();
//        } catch (Throwable t) {
//            t.printStackTrace(System.err);
//        }
//    }


    public static void startWebsocketServerTakeTwo() {
        Server server = new Server("0.0.0.0", 8095, "/endpoint/", WebsocketsCommunicatorService.class);

        try {
            server.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please press a key to stop the server.");
            reader.readLine();


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            server.stop();
        }
    }





}
