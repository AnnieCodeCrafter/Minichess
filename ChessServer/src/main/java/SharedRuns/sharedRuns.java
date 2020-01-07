package SharedRuns;

import REST.restServer;
import Websockets.WebsocketServer;

public class sharedRuns {


    public static void main(String[] args) throws Exception {
            WebsocketServer.startWebSocketServer();
            restServer.startRest();
    }
}
