package server.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.mysql.cj.jdbc.Driver;
import java.util.Date;

public class OrderDBController implements ConnectDetailsContainer{
	
	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	public void connect() {
		try {
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
			System.out.println("Attempting Connection...");
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	        System.out.println("Database Connection Succesful.");
		} catch(SQLException e) {
			System.err.println("A problem has been encountered when trying to make a connection.");
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Date parseDate(String date) {
		Date parsedDate = null;
		try {
			parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	}
	
	private void insertIntoOrderTable(int orderId, String date){
		Date parsedDate = parseDate(date);
		java.sql.Date inputDate = new java.sql.Date(parsedDate.getTime());
		try {
			String insertOrder = "INSERT INTO ORDERTABLE(ORDERID, DATE) VALUES (?,?)";
			pstmt = conn.prepareStatement(insertOrder);
			pstmt.setInt(1, orderId);
			pstmt.setDate(2, inputDate);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void insertIntoOrderLineTable(int orderId, int toolId, int supplierId, int quantity) {
		try {
			String insertOrderLine = "INSERT INTO ORDERLINETABLE(ORDERID, TOOLID, SUPPLIERID, QUANTITY) VALUES (?,?,?,?)";
			pstmt = conn.prepareStatement(insertOrderLine);
			pstmt.setInt(1, orderId);
			pstmt.setInt(2, toolId);
			pstmt.setInt(3, supplierId);
			pstmt.setInt(4, quantity);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private boolean checkIfOrderExists(String queryOrder) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(queryOrder);
			if(rs.next()) {
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// TODO:: INTEGRATE THIS METHOD IN THE SERVER SIDE
	public void queryOrderTable(int orderId, String date) {
		String queryOrder = "SELECT * FROM ORDERTABLE WHERE ORDERID = " +orderId;
		if(checkIfOrderExists(queryOrder)) {
			return;
		}
		insertIntoOrderTable(orderId, date);
	}
	
	private boolean checkIfOrderLineExists(String queryOrderLine) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(queryOrderLine);
			if(rs.next()) {
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void updateOrderLine(int toolId, int quantity) {
		String updateOrderLine = "UPDATE ORDERLINETABLE SET QUANTITY = " +quantity+ " WHERE TOOLID = " +toolId;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(updateOrderLine);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// TODO:: INTEGRATE THIS METHOD IN THE SERVER SIDE
	public void queryOrderLineTable(int orderId, int toolId, int supplierId, int quantity) {
		String queryOrderLine = "SELECT * FROM ORDERLINETABLE WHERE TOOLID = " +toolId;
		if(checkIfOrderLineExists(queryOrderLine)) {
			updateOrderLine(toolId, quantity);
			return;
		}
		insertIntoOrderLineTable(orderId, toolId, supplierId, quantity);
	}
}
