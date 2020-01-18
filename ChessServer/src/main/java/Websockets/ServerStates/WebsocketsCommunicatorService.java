package Websockets.ServerStates;


import Websockets.ServerStates.Interfaces.SendMessages;
import Websockets.ServerStates.Interfaces.ServerState;


//import javax.websocket.*;
//import javax.websocket.server.ServerEndpoint;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.Session;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.logging.Logger;

//@ServerEndpoint("/endpoint")
public class WebsocketsCommunicatorService extends WebSocketServer implements SendMessages {

    private static ServerState ServerCurrentState = null;
    private Logger logger = Logger.getLogger(this.getClass().getName());

    LoginSessionManager sessions = new LoginSessionManager(this);



    public WebsocketsCommunicatorService(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        conn.send("Welcome to the server!"); //This method sends a message to the new client
        broadcast( "new connection: " + handshake.getResourceDescriptor() ); //This method sends a message to all clients connected
        System.out.println("new connection to " + conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        System.out.println("received message from "	+ conn.getRemoteSocketAddress() + ": " + message);
    }

    @Override
    public void onMessage( WebSocket conn, ByteBuffer message ) {
        System.out.println("received ByteBuffer from "	+ conn.getRemoteSocketAddress());
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        System.err.println("an error occurred on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
    }

    @Override
    public void onStart() {
        System.out.println("server started successfully");
    }


    public static void main(String[] args) {
        String host = "0.0.0.0";
        int port = 8098;

        WebSocketServer server = new WebsocketsCommunicatorService(new InetSocketAddress(host, port));
        server.run();
    }

    public void sendMap(Map<Session, String> messages){
        System.out.println(String.format("Going to send out to %d clients",messages.size()));

        try {
            for (Session ses : messages.keySet())  {
                ses.getBasicRemote().sendText(messages.get(ses));

                System.out.println("onMessage: to =" + ses.getId() + " Message=" + messages.get(ses));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ChangeStateTo(ServerState newState) {
        ServerCurrentState = newState;
    }


    //When it connects

//    public void onConnect(Session session) {
//        System.out.println("onOpen::" + session.getId());
//        if (ServerCurrentState == null){
//            ServerCurrentState = new LoginSessionManager(this);
//        }
//        System.out.println("ping onConnect server");
//        ServerCurrentState.add(session);
//
//    }


//
//    @OnOpen
//    @Override
//    public void onOpen(Session session, EndpointConfig endpointConfig) {
//        System.out.println("onOpen::" + session.getId());
//        if (ServerCurrentState == null){
//            ServerCurrentState = new LoginSessionManager(this);
//        }
//        System.out.println("ping onConnect server");
//        ServerCurrentState.add(session);
//    }
//
//    //When a message is received
//    @OnMessage
//    @Override
//    public void onText(String message, Session session) {
//        System.out.println(String.format("Received message: %s,  from client %s",message, session.getId() ));
//        sessions.SessionMapper(message, session);
//
//    }
//
//
//
//    //When the connection is closed
//    @OnClose
//    public void onClose( Session session, CloseReason closeReason) {
//        System.out.println("onClose::" +  session.getId());
//        logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
//        sessions.remove(session);
//
//    }
//
//    //When an error is thrown
//   // TODO: IS ONERROR THE PROBLEM?
// //   @OnError
//    public void onError(Throwable cause, Session session) {
////        System.out.println("[WebSocket Session ID] : " + session.getId() + "[ERROR]: ");
//
//        cause.printStackTrace(System.err);
//
//
//    }

}
