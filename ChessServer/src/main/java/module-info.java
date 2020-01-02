module ChessServer {
    requires java.sql;
    requires gson;
    requires javax.ws.rs.api;
    requires Shared;
    requires jetty.servlet;
    requires jetty.server;
    requires jersey.container.servlet.core;
    requires javax.websocket.api;
    requires javax.websocket.server.impl;
    exports Database;
    exports REST;
    exports Websockets;

}