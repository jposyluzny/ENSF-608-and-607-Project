package client.controller;

import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class ClientController {

    private final static String HOST = "localhost";
    private final static int PORT = 7001;
    private Socket clientSocket;
    private ObjectInputStream socketInObjects;
    private BufferedReader socketInStrings;
    private PrintWriter socketOut;

    //FOR TESTING ONLY
    private BufferedReader stdIn;
    
    public ClientController() {
        try {
            clientSocket = new Socket(HOST, PORT);
            socketInObjects = new ObjectInputStream(clientSocket.getInputStream());
            socketInStrings = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            socketOut = new PrintWriter(clientSocket.getOutputStream(), true);

            //TESTING PURPOSES ONLY
            stdIn = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            System.err.print(e.getStackTrace());
        }
    }

    public void communicate() {
        boolean flag = true;

        //TESTING ONLY
        String response = "";

        try {
            while (flag) {
                System.out.println("Please enter some bullshit:");
                response = stdIn.readLine();
                socketOut.println(response);
                response = socketInStrings.readLine();
                System.out.println(response);
            }
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
    }

    public static void main(String [] args) {
        ClientController client = new ClientController();
        client.communicate();
    }

}
