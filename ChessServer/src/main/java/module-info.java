module ChessServer {
    requires java.sql;
    requires gson;
    requires javax.ws.rs.api;
    requires Shared;

    requires jersey.container.servlet.core;


    requires org.eclipse.jetty.server;
    requires org.eclipse.jetty.servlet;
    requires org.eclipse.jetty.websocket.javax.websocket.server;
    requires org.slf4j;
 //   requires javax.websocket.client.api;
    requires javax.websocket.api;


    exports Database;
    exports REST;
    exports Websockets;

}