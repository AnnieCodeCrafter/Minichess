import REST.restServer;
import Websockets.WebsocketServer;

public class Shared {


    public static void main(String[] args) throws Exception {
            WebsocketServer.startWebSocketServer();
            restServer.startRest();
    }
}
