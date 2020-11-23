package server.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.mysql.cj.jdbc.Driver;
import java.util.Date;

/**
 * This class is responsible for working with the Order and OrderLine tables in the MySQL database.
 */
public class OrderDBController implements ConnectDetailsContainer{

	/**
	 * The Connection object conn will be used to establish the connection to the database and create statements.
	 * The PreparedStatement object pstmt will be used to insert data into the Tool table in the database.
	 * The Statement object stmt will be used to execute database queries and updates.
	 * The ResultSet object rs is what will be returned when executing a query. When we need a query result to be sent back to the 
	 * client, the ResultSet object will be parsed into string arrays.
	 */
	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	
	/**
	 * The connect() method needs to be called in order to establish a connection to the database.
	 */
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
	
	/**
	 * The parseDate() method will take a date as a String, and parse it into a format that MySQL will accept.
	 * @param date is the date that needs to be parsed
	 * @return the parsed date
	 */
	private Date parseDate(String date) {
		Date parsedDate = null;
		try {
			parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	}

	/**
	 * The insertIntoOrderTable() method will take the parameters required in order to insert a new row into the table, create
	 * a PreparedStatement object, and use the object to insert the inputs into the table.
	 * @param orderId is the ID number of the order
	 * @param date is the date of the order
	 */
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
	
	/**
	 * The insertIntoOrderLineTable() method will take the parameters required in order to insert a new row into the table, create
	 * a PreparedStatement object, and use the object to insert the inputs into the table. 
	 * @param orderId is the ID number of the order
	 * @param toolId is the ID number of the tool to be ordered
	 * @param supplierId is the ID number of the supplier that the tool is to be ordered from
	 * @param quantity is the quantity of the tool to be ordered
	 */
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
	
	/**
	 * The checkIfOrderExists() method will take a query to check the order table for whether or not an order already exists with
	 * the input order number. If it does, the method returns true. If not, the method returns false.
	 * @param queryOrder is the query to be run to check
	 * @return true or false
	 */
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

	/**
	 * The queryOrderTable() method takes an order id number and a date. It constructs a query to run to check the order table. If 
	 * an order already exists with the input order number, the method will not do anything. If not, the insertIntoOrderTable() method
	 * will be called to create an order.
	 * @param orderId is the ID of the order
	 * @param date is the date of the order
	 */
	public void queryOrderTable(int orderId, String date) {
		String queryOrder = "SELECT * FROM ORDERTABLE WHERE ORDERID = " +orderId;
		if(checkIfOrderExists(queryOrder)) {
			return;
		}
		insertIntoOrderTable(orderId, date);
	}
	
	/**
	 * The checkIfOrderLineExists() method takes a query to check the orderline table for whether or not an orderline already exists for a 
	 * tool. If it does, the method returns true. If not, the method returns false.
	 * @param queryOrderLine is the query to be run to check
	 * @return true or false
	 */
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
	
	/**
	 * the updateOrderLine() method takes a tool id number and a quantity. A query will run to find the tool id in the orderline table,
	 * and that tool's order quantity will be updated with the input quantity.
	 * @param toolId is the id of the tool to update
	 * @param quantity is the quantity of the tool to order
	 */
	private void updateOrderLine(int toolId, int quantity) {
		String updateOrderLine = "UPDATE ORDERLINETABLE SET QUANTITY = " +quantity+ " WHERE TOOLID = " +toolId;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(updateOrderLine);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The queryOrderLineTable() method takes all the information required in order to build a new line in the orderline table. It will
	 * call the checkIfOrderLineExists() method on the query that is built. If the orderline already exists, it will be updated with the
	 * new quantity to be ordered. If it does not exist, the insertIntoOrderLineTable() method will be called to create a new orderline.
	 * @param orderId is the ID of the order
	 * @param toolId is the ID of the tool to be ordered
	 * @param supplierId is the ID of the supplier that the tool is to be ordered from
	 * @param quantity is the quantity of the tool to be ordered
	 */
	public void queryOrderLineTable(int orderId, int toolId, int supplierId, int quantity) {
		String queryOrderLine = "SELECT * FROM ORDERLINETABLE WHERE TOOLID = " +toolId;
		if(checkIfOrderLineExists(queryOrderLine)) {
			updateOrderLine(toolId, quantity);
			return;
		}
		insertIntoOrderLineTable(orderId, toolId, supplierId, quantity);
	}
	
	/**
	 * The close() method will close the Connection, PreparedStatement, Statement, and ResultSet objects.
	 */
	public void close() {
		try {
			pstmt.close();
			stmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
