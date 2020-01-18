package Websockets.ws;

/***
 *
 * https://developer.mozilla.org/en-US/docs/Web/API/WebSockets_API/Writing_WebSocket_client_applications
 *
 *https://docs.oracle.com/javaee/5/tutorial/doc/bnayn.html
 *
 *
infor *
 *
 */

public class WebSocketsLittleClient {

    public static void main(String[] args) throws Exception {

        var exampleSocket = new WebSocket("wss:127.0.0.1:8098", "protocolOne");
    }
}
