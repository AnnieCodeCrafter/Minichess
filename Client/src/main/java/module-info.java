module Client {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires gson;
    requires httpcore;
    requires java.logging;
    requires httpclient;


    requires Shared;
    requires tyrus.client;
    requires javax.websocket.api;
    requires java.sql;

    exports Starts;
    exports WebsocketsClient;
    exports RESTclient;

}