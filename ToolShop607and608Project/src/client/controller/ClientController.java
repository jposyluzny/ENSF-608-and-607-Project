package client.controller;

import java.net.Socket;

import client.view.InventoryGUI;
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
    
    
    public ClientController() {
        try {
            clientSocket = new Socket(HOST, PORT);
            socketInObjects = new ObjectInputStream(clientSocket.getInputStream());
            socketInStrings = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            socketOut = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void close() {
    	try {
    		socketInObjects.close();
    		socketInStrings.close();
    		socketOut.close();
    	} catch (IOException e) {
    		e.printStackTrace();
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
        ClientModelController cmc = new ClientModelController(client);
        cmc.run();
    }
}
