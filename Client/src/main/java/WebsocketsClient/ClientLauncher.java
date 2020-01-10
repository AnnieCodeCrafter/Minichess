package WebsocketsClient;

import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;




public class ClientLauncher {
    private static final int PORT = 8095;
    private static final String endpoint = "/endpoint/";
    private static final String host = "ws://0.0.0.0";

    private static CountDownLatch latch;

//    public static void startClient(Class<?> endpointClass){
//
//        try {
//            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
//            String uri = host + ":" + PORT + endpoint;
//            System.out.println("Connecting to " + uri);
//            container.connectToServer(endpointClass, URI.create(uri));
//        } catch (Exception ex) {
//            Logger.getLogger(endpointClass.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public static void startClientTake2(Class<?> endpointClass) {

            latch = new CountDownLatch(1);
            String uri = host + ":" + PORT + endpoint;
            ClientManager client = ClientManager.createClient();

            //WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            try {
                client.connectToServer(endpointClass, new URI(uri));
                latch.await();

            } catch (DeploymentException | URISyntaxException | InterruptedException e) {
                Logger.getLogger(endpointClass.getName()).log(Level.SEVERE, null, e);
                throw new RuntimeException(e);

            }

    }

    public static void main(String argv[]){
        startClientTake2(ActiveClientEndpoint.class);
    }

}
