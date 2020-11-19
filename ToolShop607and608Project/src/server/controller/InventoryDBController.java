package server.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;

public class InventoryDBController implements ConnectDetailsContainer{
	
	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	public InventoryDBController()
	{
	}

	public void connect() {
		try {
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
		} catch(SQLException e) {
			System.err.println("A problem has been encountered when trying to make a connection.");
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void createToolTable() {
		String toolTable = "CREATE TABLE IF NOT EXISTS ToolTable("
	    		+ "TOOLID INT NOT NULL,"
				+ "TYPE VARCHAR(15) NOT NULL,"
	    		+ "NAME VARCHAR(15) NOT NULL,"
	    		+ "QUANTITY INT NOT NULL,"
	    		+ "PRICE DECIMAL(10,2) NOT NULL,"
	    		+ "SUPPLIERID INT NOT NULL)"
	    		+ "PRIMARY KEY (TOOLID);";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(toolTable);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Created ToolTable in 608607Project Database");	
	}
	
	public void insertIntoToolTable(int id, String type, String name, int quantity, double price, int supplier_id) {
		try {
			String insert = "INSERT INTO TOOLTABLE(TOOLID, TYPE, NAME, QUANTITY, PRICE, SUPPLIERID) VALUES (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insert);
			pstmt.setInt(1, id);
			pstmt.setString(2, type);
			pstmt.setString(3, name);
			pstmt.setInt(4, quantity);
			pstmt.setDouble(5, price);
			pstmt.setInt(6, supplier_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void populateToolTable(String toolFileName) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(toolFileName));
			String line;
			line = reader.readLine();
			while(line != null) {
				String[] elements = line.split(";");
				insertIntoToolTable(Integer.parseInt(elements[0]),elements[1],elements[2],Integer.parseInt(elements[3]),Double.parseDouble(elements[4]),Integer.parseInt(elements[5]));
				line = reader.readLine();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		String toolFileName = "items_new.txt";
		InventoryDBController idbc = new InventoryDBController();
		idbc.connect();
		idbc.createToolTable();
		idbc.populateToolTable(toolFileName);
	}
}