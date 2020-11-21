package server.controller;

import java.net.ServerSocket;
import java.net.Socket;

import server.Model.Shop;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class ServerController {
    
    private final static int PORT = 7001;
    private Socket sSocket;
    private ServerSocket serverSocket;
    private BufferedReader socketInStrings;
    private ObjectOutputStream socketOutObjects;
    private PrintWriter socketOut;
    
    public String response = "";
    
    public ServerController() {
        try {
            serverSocket = new ServerSocket(PORT);
            sSocket = serverSocket.accept();
            socketInStrings = new BufferedReader(new InputStreamReader(sSocket.getInputStream()));
            socketOutObjects = new ObjectOutputStream(sSocket.getOutputStream());
            socketOut = new PrintWriter(sSocket.getOutputStream(), true);
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
    
    public void close() {
    	try {
    		serverSocket.close();
    		socketInStrings.close();
    		socketOutObjects.close();
    		socketOut.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public BufferedReader getSocketInStrings() {
    	return socketInStrings;
    }

	public ObjectOutputStream getSocketOutObjects() {
		return socketOutObjects;
	}

	public void setSocketOutObjects(ObjectOutputStream socketOutObjects) {
		this.socketOutObjects = socketOutObjects;
	}

	public PrintWriter getSocketOut() {
		return socketOut;
	}

	public void setSocketOut(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}
	
    public static void main(String [] args) {
        ServerController server = new ServerController();
        ServerModelController smc = new ServerModelController(server, new DBController(new InventoryDBController(), new CustomerDBController(), new OrderDBController()), new Shop());
        smc.getDataBaseController().getIdbController().connect();
        smc.run();
    }

}
