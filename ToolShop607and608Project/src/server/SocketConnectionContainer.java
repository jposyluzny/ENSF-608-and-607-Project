package server;

/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

/**
 * This class will hold the details and getter methods for the socket Host and Port data fields required for Clients to connect to
 * to the server.
 */
public interface SocketConnectionContainer {
	
	//Connection details for specifying the socket information to connect Server and Client
    final static String HOST = "localhost";
    final static int PORT = 7001;
    
    public abstract String getHost();
    
    public abstract int getPort();

}
