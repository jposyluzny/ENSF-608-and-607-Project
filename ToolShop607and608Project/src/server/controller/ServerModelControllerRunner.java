/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.controller;
import server.Model.Shop;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class extends the ServerModelController class, and implements Runnable so it can run on threads.
 */
public class ServerModelControllerRunner extends ServerModelController implements Runnable {

	/**
	 * The ServerControllerRunner constructor calls the super ServerModelController constructor. 
	 * @param server is the ServerController object
	 * @param dataBaseController is the dataBaseController object
	 * @param shop is the Shop object
	 */
	public ServerModelControllerRunner(ServerController server, DBController dataBaseController, Shop shop) {
		super(server, dataBaseController, shop);
	}

	/**
	 * The run() method will call the methods in the ServerModelController class and communicate with the client.
	 */
	public void run() {
		this.connectToDatabases();
		while (true) {
			try {
				ArrayList<String[]> rawTools;
				ArrayList<String[]> rawCustomersOut;
				ArrayList<String> rawCustomersIn;
				String [] rawTool;
				String [] rawCustomer;
				String name;
				int id;

				this.getShop().clearLists();

				String input = this.listenForStringMarkers();
				switch (input) {			
				case "List all Tools":
					this.sendMarkerStringToClient("List all Tools");
					rawTools = this.getAllToolsFromDatabase();
					this.buildTools(rawTools);
					this.writeToolsToClient();
					break;
				case "Search Tool by Name":
					this.sendMarkerStringToClient("Show Tool");
					name = this.listenForClientStringInfo();
					rawTool = this.getSingleToolFromDatabase(name);
					this.buildTools(rawTool);
					this.writeToolsToClient();
					break;
				case "Search Tool by ID":
					this.sendMarkerStringToClient("Show Tool");
					id = Integer.parseInt(this.listenForClientStringInfo());
					rawTool = this.getSingleToolFromDatabase(id);
					this.buildTools(rawTool);
					this.writeToolsToClient();
					break;
				case "Check Quantity":
					this.sendMarkerStringToClient("Check Quantity");
					name = this.listenForClientStringInfo();
					rawTool = this.getSingleToolFromDatabase(name);
					this.buildTools(rawTool);
					this.writeToolsToClient();
					break;
				case "Decrease Quantity":
					this.sendMarkerStringToClient("Decrease Quantity");
					name = this.listenForClientStringInfo();
					this.decreaseToolQuantity(name);
					this.orderMoreTools(name);
					break;
				case "Add new Customer":
					this.sendMarkerStringToClient("Add new Customer");
					rawCustomersIn = (ArrayList<String>) this.listenForClientObjectsInfo();
					this.addNewCustomerToDatabase(rawCustomersIn);
					this.sendMarkerStringToClient("Customer information added to database successfully.");
					break;
				case "Update existing Customer":
					this.sendMarkerStringToClient("Update existing Customer");
					rawCustomersIn = (ArrayList<String>) this.listenForClientObjectsInfo();
					this.updateExistingCustomerInDatabase(rawCustomersIn);
					this.sendMarkerStringToClient("Customer information updated successfully.");
					break;
				case "Remove customer from DB":
					this.sendMarkerStringToClient("Remove customer from DB");
					id = Integer.parseInt(this.listenForClientStringInfo());
					this.removeExistingCustomerFromDatabase(id);
					this.sendMarkerStringToClient("Customer successfully removed from database.");
					break;
				case "Search for Customer by ID":
					this.sendMarkerStringToClient("Show Customers");
					id = Integer.parseInt(this.listenForClientStringInfo());
					rawCustomer = this.getSingleCustomerFromDatabase(id);
					this.buildCustomers(rawCustomer);
					this.writeCustomersToClient();
					break;
				case "Search for Customer by last name":
					this.sendMarkerStringToClient("Show Customers");
					name = this.listenForClientStringInfo();
					rawCustomer = this.getSingleCustomerFromDatabase(name);
					this.buildCustomers(rawCustomer);
					this.writeCustomersToClient();
					break;
				case "Search for all Customers by type":
					this.sendMarkerStringToClient("Show Customers");
					name = this.listenForClientStringInfo();
					rawCustomersOut = this.getAllCustomersByTypeFromDatabase(name);
					this.buildCustomers(rawCustomersOut);
					this.writeCustomersToClient();
					break;
				case "Dummy String":
					break;
				default:
					System.err.println("Something went wrong...");
				}
			} catch (NumberFormatException e) {
				this.sendMarkerStringToClient("Wrong input");
				continue;
			} catch (IOException | ClassNotFoundException e) {
				break;
			}
		}
		this.close();
		this.getServerController().close();
	}
	
}

