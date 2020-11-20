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
    
    public ServerController() {
        try {
            serverSocket = new ServerSocket(PORT);
            sSocket = serverSocket.accept();
            socketInStrings = new BufferedReader(new InputStreamReader(sSocket.getInputStream()));
            socketOutObjects = new ObjectOutputStream(sSocket.getOutputStream());
            socketOut = new PrintWriter(sSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.err.print(e.getStackTrace());
        }
    }

    //TODO: THREAD PER USER
    public void communicate() {
        boolean flag = true;
        String response = "";
        try {
            while (flag) {
                response = socketInStrings.readLine();
                System.out.println(response);
                response += "ADDED ON SERVER SIDE";
                socketOut.println(response);
            }
        } catch (IOException e) {
            System.err.println(e.getStackTrace());
        }
        finally {
        	this.close();
        }
    }
    
    public void close() {
    	try {
    		serverSocket.close();
    		socketInStrings.close();
    		socketOutObjects.close();
    		socketOut.close();
    	} catch (IOException e) {
    		System.err.println(e.getStackTrace());
    	}
    }

    public static void main(String [] args) {
        ServerController server = new ServerController();
        new ServerModelController(server, new DBController(new InventoryDBController(), new CustomerDBController(), new OrderDBController()), new Shop());
        server.communicate();
    }

}
