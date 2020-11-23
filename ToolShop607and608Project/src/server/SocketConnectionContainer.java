package server;

public interface SocketConnectionContainer {
	
	//Connection details for specifying the socket information to connect Server and Client
    final static String HOST = "localhost";
    final static int PORT = 7001;
    
    public abstract String getHost();
    
    public abstract int getPort();

}
