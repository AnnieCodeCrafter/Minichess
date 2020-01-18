package Websockets.ws;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;

import static java.net.http.WebSocket.NORMAL_CLOSURE;


public class WsClientlauncher {

    public static void main(String[] args) throws Exception {
        CompletableFuture<WebSocket> server_cf = HttpClient.
                newHttpClient().
                newWebSocketBuilder().
                buildAsync(URI.create("ws://localhost:4567/echo"),
                        new WebSocketClient());

        server_cf.join();

    }
}
