module ChessServer {
    requires java.sql;
    requires gson;
    requires javax.ws.rs.api;
    requires Shared;



    requires org.eclipse.jetty.servlet;
    requires org.eclipse.jetty.websocket.javax.websocket.server;
    requires org.slf4j;

   requires javax.websocket.api;
    requires jetty.server;
    requires jersey.container.servlet.core;
    exports Websockets;

}