package server.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

public class CustomerDBController  implements ConnectDetailsContainer{
	
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
	
//	public void updateCustomerInfo(//customer information string? {
		// ##################### TODO ####################### //
//	}
	
	public void removeCustomer(int id) {
		try {
			String deleteCust = "DELETE FROM CUSTOMERTABLE WHERE CUSTOMERID = "+id+"";
			stmt = conn.createStatement();
			stmt.executeUpdate(deleteCust);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String[] resultsToArray(ResultSet rs) {
		String[] arr = null;
		try {
			arr = new String[7];
			for(int i = 0; i < arr.length; i++) {
				arr[i] = rs.getString(i+1);
			}
		} catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return arr;
	}
	
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
	
	public String[] queryById(int id) {
		String queryId = "SELECT * FROM CUSTOMERTABLE WHERE CUSTOMERID = " +id+ "";
		return executeCustomerQuery(queryId);
	}
	
	public String[] queryByName(String custName) {
		String queryName = "SELECT * FROM CUSTOMERTABLE WHERE NAME = '" +custName+ "'";
		return executeCustomerQuery(queryName);
	}
	
	public String[] queryByType(String type) {
		String queryType = "SELECT * FROM CustomerTable WHERE TYPE = '" +type+ "'";
		return executeCustomerQuery(queryType);
	}
	
	
}