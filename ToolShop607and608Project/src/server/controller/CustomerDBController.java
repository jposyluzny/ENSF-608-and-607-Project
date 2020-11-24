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
import java.util.ArrayList;
import com.mysql.cj.jdbc.Driver;

/**
 * This class is responsible for working with the Customer table in the MySQL database.
 */
public class CustomerDBController  implements ConnectDetailsContainer{

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
	 * The insertIntoCustomerTable() method will take the parameters required in order to insert a new row into the table, create a
	 * PreparedStatement object, and use the object to insert the inputs into the table.
	 * @param id is the ID number of the customer
	 * @param firstName is the first name of the customer
	 * @param lastName is the last name of the customer
	 * @param address is the address of the customer
	 * @param postalCode is the postal code of the customer
	 * @param phoneNum is the phone number of the customer
	 * @param type is the type of the customer, R for residential or C for commercial
	 */
	public void insertIntoCustomerTable(int id, String firstName, String lastName, String address, String postalCode, String phoneNum, String type) {
		try {
			String insertCust = "INSERT INTO CUSTOMERTABLE(CUSTOMERID, FIRSTNAME, LASTNAME, ADDRESS, POSTALCODE, PHONENUMBER, TYPE) VALUES (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(insertCust);
			pstmt.setInt(1, id);
			pstmt.setString(2, firstName);
			pstmt.setString(3, lastName);
			pstmt.setString(4, address);
			pstmt.setString(5, postalCode);
			pstmt.setString(6, phoneNum);
			pstmt.setString(7, type);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The findMaximumId() method queries the customer database to find the highest value for customer id, which will be used in the addNewCustomer() method.
	 * @return the maximum value for customer id
	 */
	public int findMaximumId() {
		int maximum = 0;
		String findMaxId = "SELECT MAX(CUSTOMERID) FROM CUSTOMERTABLE";
		maximum = Integer.parseInt(executeCustomerQuery(findMaxId)[0]);
		return maximum;
	}

	/**
	 * The addNewCustomer() method takes the value returned from the findMaximumId() method, increments it by 1, and uses it inside the call to the 
	 * insertIntoCustomerTable method, so each new customer's id is guaranteed to be unique.
	 * @param firstName is the first name of the new customer
	 * @param lastName is the last name of the new customer
	 * @param address is the address of the new customer
	 * @param postalCode is the postal code of the new customer
	 * @param phoneNum is the phone number of the new customer
	 * @param type is the type of the new customer
	 */
	public void addNewCustomer(String firstName, String lastName, String address, String postalCode, String phoneNum, String type) {
		int newId = findMaximumId() + 1;
		insertIntoCustomerTable(newId, firstName, lastName, address, postalCode, phoneNum, type);
	}
	
	/**
	 * The updateCustomerInfo() method will take the changeable parameters of a customer, as well as their ID number, which will be
	 * used to query the database. 
	 * @param id is the ID number of the customer
	 * @param firstName is the first name of the customer
	 * @param lastName is the last name of the customer
	 * @param address is the address of the customer
	 * @param postalCode is the postal code of the customer
	 * @param phoneNum is the phone number of the customer
	 */
	public void updateCustomerInfo(int id, String firstName, String lastName, String address, String postalCode, String phoneNum) {
		try {
			String updateCust = "UPDATE CUSTOMERTABLE SET " 
					+"FIRSTNAME = '" + firstName + "', "
					+"LASTNAME = '" + lastName + "', "
					+"ADDRESS = '" + address + "', "
					+"POSTALCODE = '" + postalCode + "', "
					+"PHONENUMBER = '" + phoneNum + "' "
					+"WHERE CUSTOMERID = " + id + ";";
			stmt = conn.createStatement();
			stmt.executeUpdate(updateCust);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The removeCustomer() method will take the ID of the customer to be removed, and then remove that customer from the table.
	 * @param id is the ID number of the customer to be deleted
	 */
	public void removeCustomer(int id) {
		try {
			String deleteCust = "DELETE FROM CUSTOMERTABLE WHERE CUSTOMERID = "+id+"";
			stmt = conn.createStatement();
			stmt.executeUpdate(deleteCust);
		}catch(SQLException e) {
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
	 * The queryCustomerTypes() method will search the table of customers, find the customers that match the input type, call
	 * the resultsToArray() method on every row in the result, and add each returned array to an ArrayList, which is returned
	 * at the end.
	 * @param type is the type of customer to be searched for
	 * @return the ArrayList of String arrays that are populated with the query results
	 */
	public ArrayList<String[]> queryCustomerTypes(String type) {
		ArrayList<String[]> customerList = new ArrayList<String[]>();
		String queryCustomerTypes = "SELECT * FROM CUSTOMERTABLE WHERE TYPE = '" +type+ "'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(queryCustomerTypes);
			if(rs.isBeforeFirst()) {
				while(rs.next()) {
					customerList.add(resultsToArray(rs));
				}
			}
			else {
				System.out.println("\nSearch failed to find data in ToolTable");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}
	
	/**
	 * The executeCustomerQuery() method will take a query as a parameter, try to execute the query, and then return the result of
	 * the query in an array of Strings.
	 */
	public String[] executeCustomerQuery(String queryCust) {
		String[] customerInfo = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(queryCust);
			if(rs.isBeforeFirst()) {
				rs.next();
				customerInfo = resultsToArray(rs);
			}
			else {
				System.out.println("\nSearch failed to find customer");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerInfo;
	}
	
	/**
	 * The queryById() method will take a customer id as a parameter, and will build the query based off of that.
	 * @param id is the id of the customer to be searched for
	 * @return the result of the executeCustomerQuery() method, which is an array of Strings
	 */
	public String[] queryById(int id) {
		String queryId = "SELECT * FROM CUSTOMERTABLE WHERE CUSTOMERID = " +id+ "";
		return executeCustomerQuery(queryId);
	}
	
	/**
	 * The queryByName() method will take a customer's last name as a parameter, and will build the query based off of that.
	 * @param lastName is the last name of the customer to be searched for
	 * @return the result of the executeCustomerQuery() method, which is an array of Strings
	 */
	public String[] queryByName(String lastName) {
		String queryName = "SELECT * FROM CUSTOMERTABLE WHERE LASTNAME = '" +lastName+ "'";
		return executeCustomerQuery(queryName);
	}
	
	/**
	 * The close() method will close the Connection, PreparedStatement, Statement, and ResultSet objects.
	 */
	public void close() {
		try {
			stmt.close();
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}