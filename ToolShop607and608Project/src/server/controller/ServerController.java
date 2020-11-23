package server.controller;

import server.Model.Shop;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;

public class ServerController implements Runnable, server.SocketConnectionContainer {
    
    private Socket sSocket;
    private ServerSocket serverSocket;
	private ExecutorService pool;
    
    public ServerController() {
    	try {
    		serverSocket = new ServerSocket(PORT);
    		System.out.println("Server running on PORT number: " + PORT); 
    		pool = Executors.newCachedThreadPool();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
	public void run() {
		try {
			while (true) {
				sSocket = serverSocket.accept();
	            System.out.println("Connection accepted by the server.");
	            ServerModelControllerRunner smcr = new ServerModelControllerRunner(this, new DBController(new InventoryDBController(), 
	            							new CustomerDBController(), new OrderDBController()), new Shop());
	            pool.execute(smcr);
			}
		} catch (IOException e) {
			this.close();
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
	
    public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	public String getHost() {
		return HOST;
	}

	public int getPort() {
		return PORT;
	}
	
    public static void main(String [] args) {
        ServerController server = new ServerController();
        server.run();
    }

}
