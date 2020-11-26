/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.controller;

/**
 * This interface contains the JDBC driver, database url, and database credentials.
 */

public interface ConnectDetailsContainer {
	
		// JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost:3306/608607project";

	   //  Database credentials
	   static final String USERNAME = "josh";
	   static final String PASSWORD = "password";
}
