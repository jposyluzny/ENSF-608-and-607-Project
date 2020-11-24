package client.controller;

/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

import server.Model.Shop;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * This class will handle the communication between the client and the server.
 */
public class ClientController implements server.SocketConnectionContainer {

    private Socket clientSocket;
    private ObjectInputStream socketInObjects;
    private BufferedReader socketInStrings;
    private PrintWriter socketOut;
    private ObjectOutputStream socketOutObjects;
    
    /**
     * This constructor will build all of the reader and writer objects that will handle socket communication with the server.
     */
    public ClientController() {
        try {
            this.setClientSocket(new Socket(HOST, getPort()));
            this.setSocketInObjects(new ObjectInputStream(clientSocket.getInputStream()));
            this.setSocketInStrings(new BufferedReader(new InputStreamReader(clientSocket.getInputStream())));
            this.setSocketOut(new PrintWriter(clientSocket.getOutputStream(), true));
            this.setSocketOutObjects(new ObjectOutputStream(clientSocket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This will close all of the reader and writer objects to prevent any potential memory issues.
     */
    public void close() {
    	try {
    		socketInObjects.close();
    		socketInStrings.close();
    		socketOut.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
	public Socket getClientSocket() {
		return clientSocket;
	}

	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
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
	
	public ObjectOutputStream getSocketOutObjects() {
		return socketOutObjects;
	}

	public void setSocketOutObjects(ObjectOutputStream socketOutObjects) {
		this.socketOutObjects = socketOutObjects;
	}
	
	public String getHost() {
		return HOST;
	}

	public int getPort() {
		return PORT;
	}
	
	/**
	 * This will physically start the client side application.
	 * @param args is an array of strings that will be read from an external run command.
	 */
    public static void main(String [] args) {
        ClientController client = new ClientController();
        ClientModelControllerRunner cmcr = new ClientModelControllerRunner(client, new Shop());
        cmcr.run();
    }

}
