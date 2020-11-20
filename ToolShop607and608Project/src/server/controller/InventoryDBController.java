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
		String toolTable = "CREATE TABLE IF NOT EXISTS TOOLTABLE("
	    		+ "TOOLID INT NOT NULL,"
	    		+ "NAME VARCHAR(15) NOT NULL,"
	    		+ "QUANTITY INT NOT NULL,"
	    		+ "PRICE DECIMAL(10,2) NOT NULL,"
	    		+ "SUPPLIERID INT NOT NULL,"
	    		+ "TYPE VARCHAR(15) NOT NULL,"
	    		+ "POWERTYPE VARCHAR(4),"
	    		+ "PRIMARY KEY (TOOLID));";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(toolTable);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Created ToolTable in 608607Project Database");	
	}
	
	public void insertIntoToolTable(int id, String name, int quantity, double price, int supplierId, String type, String powerType) {
		try {
			String insertTool = "INSERT INTO TOOLTABLE(TOOLID, NAME, QUANTITY, PRICE, SUPPLIERID, TYPE, POWERTYPE) VALUES (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insertTool);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, quantity);
			pstmt.setDouble(4, price);
			pstmt.setInt(5, supplierId);
			pstmt.setString(6, type);
			pstmt.setString(7, powerType);
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
				insertIntoToolTable(Integer.parseInt(elements[0]),elements[1],Integer.parseInt(elements[2]),Double.parseDouble(elements[3]),Integer.parseInt(elements[4]),elements[5],elements[6]);
				line = reader.readLine();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkPopulated(ResultSet rs) {
		try {
			if(rs.isBeforeFirst()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String[] resultsToArray(ResultSet rs) {
		String[] arr = null;
		try {
			arr = new String[7];
			arr[0] = rs.getString(1);
			arr[1] = rs.getString(2);
			arr[2] = rs.getString(3);
			arr[3] = rs.getString(4);
			arr[4] = rs.getString(5);
			arr[5] = rs.getString(6);
			arr[6] = rs.getString(7);
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return arr;
	}
	
	public void printArrayList(ArrayList<String[]> arrList) {
		for(int i = 0; i < arrList.size(); i++) {
			String id = arrList.get(i)[0];
			String name = arrList.get(i)[1];
			String quantity = arrList.get(i)[2];
			String price = arrList.get(i)[3];
			String supplierid = arrList.get(i)[4];
			String type = arrList.get(i)[5];
			String powerType = arrList.get(i)[6];
			System.out.println(id + " " + name + " " + quantity + " " + price + " " + supplierid + " " + type + " " + powerType);
		}
	}
	
	public void printArray(String[] arr) {
		System.out.println("");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
	public ArrayList<String[]> queryAllTools() {
		ArrayList<String[]> toolList = new ArrayList<String[]>();
		String queryAllTools = "SELECT * FROM ToolTable;";
		try {
			rs = pstmt.executeQuery(queryAllTools);
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					toolList.add(resultsToArray(rs));
				}
			}
			else {
				System.out.println("\nSearch failed to find data in ToolTable");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		printArrayList(toolList);
		return toolList;
	}
	
	public String[] executeToolQuery(String queryTool) {
		String[] toolInfo = null;
		try {
			rs = pstmt.executeQuery(queryTool);
			if(rs.isBeforeFirst()) {
				rs.next();
				toolInfo = resultsToArray(rs);
				printArray(toolInfo);
			}

			else {
				System.out.println("\nSearch failed to find tool");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toolInfo;
	}
	
	public String[] queryByName(String toolName) {
		String queryName = "SELECT * FROM TOOLTABLE WHERE NAME = '" +toolName+ "'";
		return executeToolQuery(queryName);
	}
	
	public String[] queryById(int id) {
		String queryId = "SELECT * FROM TOOLTABLE WHERE TOOLID = " +id+ "";
		return executeToolQuery(queryId);
	}
	
	public static void main(String[] args) {
		String toolFileName = "items_new.txt";
		InventoryDBController idbc = new InventoryDBController();
		idbc.connect();
		idbc.createToolTable();
		idbc.populateToolTable(toolFileName);
		idbc.queryAllTools();
		idbc.queryByName("Barn Bins");
		idbc.queryById(1035);
		idbc.queryById(4000);
	}
}