module Client {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.base;
    requires gson;
    requires httpcore;
    requires java.logging;
    requires httpclient;

    requires javax.websocket.api;
    requires Shared;

    exports Starts;
    exports WebsocketsClient;
    exports RESTclient;

}