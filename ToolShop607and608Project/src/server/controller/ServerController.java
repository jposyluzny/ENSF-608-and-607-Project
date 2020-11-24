/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.controller;
import server.Model.Shop;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;

/**
 * This class is responsible for running the server. The socket host and port information is supplied by the SocketConnectionContainer interface,
 * and the ServerController implements Runnable to allow the server to run on threads.
 */
public class ServerController implements Runnable, server.SocketConnectionContainer {
    
    private Socket sSocket;
    private ServerSocket serverSocket;
	private ExecutorService pool;
    
	/**
	 * The ServerController constructor creates a new ServerSocket object, and assigns the ExecutorService object pool a new cached thread pool.
	 */
    public ServerController() {
    	try {
    		serverSocket = new ServerSocket(PORT);
    		System.out.println("Server running on PORT number: " + PORT); 
    		pool = Executors.newCachedThreadPool();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    /**
     * The run() method calls the ServerModelControllerRunner constructor, and the ExecutorService object pool executes the ServerModelControllerRunner object.
     */
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
    		pool.shutdown();
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
