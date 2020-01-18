package WebsocketsClient;

import javax.websocket.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@ClientEndpoint
public class ActiveClientEndpoint {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private Session connection;

    private ActiveClient myclient = new ActiveClient(this);


    public void send(String message){
        try {
            String s = connection.getId();
            System.out.println(String.format("Sending message %s, to %s: ",message, connection.getId()));
            connection.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            Logger.getLogger(ActiveClientEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        connection = session;
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
        System.out.println("Yes it did connect");
    }

    @OnMessage
    public void processMessage(String message) {
        System.out.println("Received message in client: " + message);
        myclient.handleIncoming(message);

    }

    @OnError
    public void processError(Throwable t) {

        t.printStackTrace();
    }


    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        logger.info(String.format("Session %s close because of %s", session.getId(), closeReason));
    }

}
