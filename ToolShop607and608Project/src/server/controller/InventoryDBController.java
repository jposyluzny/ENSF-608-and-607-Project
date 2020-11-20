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
import java.util.ArrayList;

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
	    		+ "SUPPLIERID INT NOT NULL,"
	    		+ "PRIMARY KEY (TOOLID));";
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
			pstmt.executeUpdate();
		} catch (SQLException e) {
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
	
	public void resultsToArray(ArrayList<String[]> arrList, ResultSet rs) {
		String[] arr = null;
		try {
			while(rs.next()) {
				arr = new String[6];
				arr[0] = rs.getString(1);
				arr[1] = rs.getString(2);
				arr[2] = rs.getString(3);
				arr[3] = rs.getString(4);
				arr[4] = rs.getString(5);
				arr[5] = rs.getString(6);
				arrList.add(arr);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void printArrayList(ArrayList<String[]> arrList) {
		for(int i = 0; i < arrList.size(); i++) {
			String id = arrList.get(i)[0];
			String type = arrList.get(i)[1];
			String name = arrList.get(i)[2];
			String quantity = arrList.get(i)[3];
			String price = arrList.get(i)[4];
			String supplierid = arrList.get(i)[5];
			System.out.println(id + " " + type + " " + name + " " + quantity + " " + price + " " + supplierid);
		}
	}
	
	public ArrayList<String[]> queryAllTools() {
		ArrayList<String[]> toolList = new ArrayList<String[]>();
		String queryAllTools = "SELECT * FROM ToolTable;";
		try {
			rs = pstmt.executeQuery(queryAllTools);
			if(rs.isBeforeFirst()) {
				resultsToArray(toolList, rs);
			}
			else {
				System.out.println("Search failed to find data in ToolTable");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printArrayList(toolList);
		return toolList;
	}
	
	public String[] queryByName()
	
	public static void main(String[] args) {
		String toolFileName = "items_new.txt";
		InventoryDBController idbc = new InventoryDBController();
		idbc.connect();
		idbc.createToolTable();
		idbc.populateToolTable(toolFileName);
		idbc.queryAllTools();
	}
}