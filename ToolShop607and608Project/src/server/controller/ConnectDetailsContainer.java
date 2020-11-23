package server.controller;

//NOTE: THE DB_URL AND CREDENTIALS NEED TO BE CHANGED BETWEEN JOSH AND PATRICK WHEN BEING WORKED ON TO CONNECT TO DB

public interface ConnectDetailsContainer {
	
		// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/608607project";

	   //  Database credentials
	   static final String USERNAME = "root";
	   static final String PASSWORD = "password";
}
