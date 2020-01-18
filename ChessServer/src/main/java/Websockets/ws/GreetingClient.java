package Websockets.ws;

// File Name GreetingClient.java
import java.net.*;
import java.io.*;

public class GreetingClient {

    public GreetingClient(String serverName, int port){
        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);

            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            System.out.println("Server says " + in.readUTF());
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String [] args) {
        String serverName = "localhost";
        int port = 8098;//Integer.parseInt(args[1]);
        GreetingClient gs = new GreetingClient(serverName, port);
    }
}