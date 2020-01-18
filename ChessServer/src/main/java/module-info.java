module ChessServer {
    requires java.sql;
    requires gson;

    requires Shared;



    requires org.eclipse.jetty.servlet;
   // requires org.eclipse.jetty.websocket.javax.websocket.server;
    requires org.slf4j;

   requires javax.websocket.api;
   requires org.eclipse.jetty.server;
    requires javax.servlet.api;
   requires tyrus.server;
    requires java.net.http;
    requires Java.WebSocket;
   // exports Websockets;


}