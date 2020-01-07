module ChessServer {
    requires java.sql;
    requires gson;

    requires Shared;



    requires org.eclipse.jetty.servlet;
    requires org.eclipse.jetty.websocket.javax.websocket.server;
    requires org.slf4j;

   requires javax.websocket.api;
    requires org.eclipse.jetty.server;
    exports Websockets;

}