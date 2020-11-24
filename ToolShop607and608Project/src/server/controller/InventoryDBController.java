/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.jdbc.Driver;
import java.util.ArrayList;

/**
 * This class is responsible for working with the Tools table in the MySQL database.
 */

public class InventoryDBController implements ConnectDetailsContainer{
	
	/**
	 * The Connection object conn will be used to establish the connection to the database and create statements.
	 * The PreparedStatement object pstmt will be used to insert data into the Tool table in the database.
	 * The Statement object stmt will be used to execute database queries and updates.
	 * The ResultSet object rs is what will be returned when executing a query. When we need a query result to be sent back to the 
	 * client, the ResultSet object will be parsed into string arrays.
	 * The ResultSetMetaData object rsmd will be used to find the amount of columns in the ResultSet in order to dynamically
	 * size the result arrays in the resultsToArray() method.
	 */
	private Connection conn;
	private PreparedStatement pstmt;
	private Statement stmt;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	
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
	 * The insertIntoToolTable() method will take the parameters required in order to insert a new row into the table, create a 
	 * PreparedStatement object, and use the object to insert the inputs into the table.
	 * @param id is the ID number of the tool
	 * @param name is the name of the tool
	 * @param quantity is the quantity of the tool
	 * @param price is the price of the tool
	 * @param supplierId is the supplierId of the tool
	 * @param type is the type of the tool
	 * @param powerType is the powerType of the tool
	 */
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
	
	/**
	 * The resultsToArray() method will take a ResultSet object, which is the result of a query, and parse each row of the ResultSet into
	 * an array of strings.
	 * @param rs is the ResultSet that is returned after a query
	 * @return an array of strings, populated with the results of the query
	 */
	public String[] resultsToArray(ResultSet rs) {
		String[] arr = null;
		try {
			rsmd = rs.getMetaData();
			arr = new String[rsmd.getColumnCount()];
			for(int i = 0; i < arr.length; i++) {
				arr[i] = rs.getString(i+1);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return arr;
	}
	
	/**
	 * The queryAllTools() method will search the entire table of tools, call the resultsToArray() method on every row in the table,
	 * and add each returned array to an ArrayList, which is returned at the end.
	 * @return the ArrayList of String arrays that are populated with all of the tool data
	 */
	public ArrayList<String[]> queryAllTools() {
		ArrayList<String[]> toolList = new ArrayList<String[]>();
		String queryAllTools = "SELECT * FROM TOOLTABLE";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(queryAllTools);
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
		return toolList;
	}
	
	/**
	 * The executeToolQuery() method will take a query as a parameter, try to execute the query, and then return the result of
	 * the query in an array of Strings.
	 * @param queryTool is the query to be executed
	 * @return the String array that is found as a result of the query
	 */
	public String[] executeToolQuery(String queryTool) {
		String[] toolInfo = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(queryTool);
			if(rs.isBeforeFirst()) {
				rs.next();
				toolInfo = resultsToArray(rs);
			}

			else {
				System.out.println("\nSearch failed to find tool");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return toolInfo;
	}
	
	/**
	 * The queryByName() method will take a tool name as a parameter, and will build the query based off of that.
	 * @param toolName is the name of the tool to be searched for
	 * @return the result of the executeToolQuery() method, which is an array of Strings
	 */
	public String[] queryByName(String toolName) {
		String queryName = "SELECT * FROM TOOLTABLE WHERE NAME = '" +toolName+ "'";
		return executeToolQuery(queryName);
	}
	
	/**
	 * The queryById method will take a tool id as a parameter, and will build the query based off of that.
	 * @param id is the id of the tool to be searched for
	 * @return the result of the executeToolQuery() method, which is an array of Strings
	 */
	public String[] queryById(int id) {
		String queryId = "SELECT * FROM TOOLTABLE WHERE TOOLID = " +id;
		return executeToolQuery(queryId);
	}
	
	/**
	 * The decreaseQuantity() method will take a tool name as a parameter, and will build the update statement based off of that.
	 * @param toolName is the name of the tool whose quantity will be reduced
	 */
	public void decreaseQuantity(String toolName) {
        String queryId = "UPDATE TOOLTABLE SET QUANTITY = QUANTITY-1 WHERE NAME = '" +toolName+ "'";
        executeToolUpdate(queryId);
    }
	
	/**
	 * The updateQuantity() method will take the name of a tool and the quantity of that tool, and then the quantity of that tool
	 * will be updated to the input quantity in the SQL database.
	 * @param name is the name of the tool to be queried
	 * @param quantity is the quantity of the tool to be set in the database
	 */
	public void updateQuantity(String name, int quantity) {
		String updateToolQuantity = "UPDATE TOOLTABLE SET QUANTITY = " +quantity+ " WHERE NAME = '" +name+"'";
		executeToolUpdate(updateToolQuantity);
	}
	
	/**
	 * The executeToolUpdate() method will take an update query String, and execute it.
	 * @param updateQuery is the update query to be executed.
	 */
	public void executeToolUpdate(String updateQuery) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(updateQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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