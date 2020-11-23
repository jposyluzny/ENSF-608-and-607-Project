package server.controller;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import server.Model.Shop;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class ServerController implements Runnable {
    
    private final static int PORT = 7001;
    private Socket sSocket;
    private ServerSocket serverSocket;
    private ExecutorService pool;
    
    public String response = "";
    
    public ServerController() {
    	try {
    	serverSocket = new ServerSocket(PORT);
    	System.out.println("Server running on PORT number: " + PORT); 
    	pool = Executors.newCachedThreadPool();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
	@Override
	public void run() {
		try {
			while (true) {
				sSocket = serverSocket.accept();
	            System.out.println("Connection accepted by the server.");
	            ServerModelController smc = new ServerModelController(this, new DBController(new InventoryDBController(), new CustomerDBController(), new OrderDBController()), new Shop());
	            pool.execute(smc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
 	}
    
    public void close() {
    	try {
    		serverSocket.close();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }

	public Socket getsSocket() {
		return sSocket;
	}

	public void setsSocket(Socket sSocket) {
		this.sSocket = sSocket;
	}
	
    public static void main(String [] args) {
        ServerController server = new ServerController();
        server.run();
    }

}
