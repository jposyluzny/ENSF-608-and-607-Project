package client.controller;

import server.Model.*;

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
    
    public String response = "";

    //FOR TESTING ONLY
    public BufferedReader stdIn;
    
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
    
    //TODO: THREAD PER USER
    public void communicate() {
            while (true) {
                if (response.equals("Quit"))
                	break;
            }
        	this.close();
    }
    
    public void close() {
    	try {
    		socketInObjects.close();
    		socketInStrings.close();
    		socketOut.close();
    	} catch (IOException e) {
    		System.err.println(e.getStackTrace());
    	}
    }
    
	public ObjectInputStream getSocketInObjects() {
		return socketInObjects;
	}

	public void setSocketInObjects(ObjectInputStream socketInObjects) {
		this.socketInObjects = socketInObjects;
	}

	public BufferedReader getSocketInStrings() {
		return socketInStrings;
	}

	public void setSocketInStrings(BufferedReader socketInStrings) {
		this.socketInStrings = socketInStrings;
	}

	public PrintWriter getSocketOut() {
		return socketOut;
	}

	public void setSocketOut(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}

    public static void main(String [] args) {
        ClientController client = new ClientController();
        new ClientModelController(client);
        client.communicate();
    }

}
