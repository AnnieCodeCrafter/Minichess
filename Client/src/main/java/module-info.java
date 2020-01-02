module Client {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires gson;
    requires httpcore;
    requires java.logging;
    requires httpclient;
    requires Shared;
    requires javax.websocket.api;

    exports Starts;
    exports WebsocketsClient;
    exports RESTclient;

}