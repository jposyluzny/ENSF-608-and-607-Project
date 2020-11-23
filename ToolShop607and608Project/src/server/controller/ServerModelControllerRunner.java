package server.controller;

import server.Model.Shop;
import java.io.IOException;
import java.util.ArrayList;

public class ServerModelControllerRunner extends ServerModelController implements Runnable {

	public ServerModelControllerRunner(ServerController server, DBController dataBaseController, Shop shop) {
		super(server, dataBaseController, shop);
	}
	
	public void run() {
		try {
			
			this.connectToDatabases();
			
			while (true) {
				
				String input = this.listenForStringMarkers();
				
				switch (input) {			
					case "List all Tools":
						this.sendMarkerStringToClient("List all Tools");
						ArrayList<String[]> rawTools = this.getAllToolsFromDatabase();
						this.buildTools(rawTools);
						this.writeToolsToClient();
						break;
					case "Search Tool by Name":
						this.sendMarkerStringToClient("Show Tool");
						String name = this.listenForClientStringInfo();
						String [] rawTool = this.getSingleToolFromDatabase(name);
						this.buildTools(rawTool);
						this.writeToolsToClient();
						break;
					case "Search Tool by ID":
						this.sendMarkerStringToClient("Show Tool");
						//NEED TO ERROR HANDLE THIS STATEMENT HERE AS WELL**************************************************
						int id = Integer.parseInt(this.listenForClientStringInfo());
						String [] rawTool2 = this.getSingleToolFromDatabase(id);
						this.buildTools(rawTool2);
						this.writeToolsToClient();
						break;
					case "Check Quantity":
						this.sendMarkerStringToClient("Check Quantity");
						String name2 = this.listenForClientStringInfo();
						String [] rawTool3 = this.getSingleToolFromDatabase(name2);
						this.buildTools(rawTool3);
						this.writeToolsToClient();
						break;
					case "Decrease Quantity":
						this.sendMarkerStringToClient("Decrease Quantity");
						String name3 = this.listenForClientStringInfo();
						this.decreaseToolQuantity(name3);
						this.sendMarkerStringToClient("Tool quantity decreased successfully.");
						break;
					case "Add new Customer":
						this.sendMarkerStringToClient("Add new Customer");
						ArrayList<String> rawCustomersIn = (ArrayList<String>) this.listendForClientObjectsInfo();
						this.addNewCustomerToDatabase(rawCustomersIn);
						this.sendMarkerStringToClient("Customer information added to database successfully.");
						break;
					case "Update existing Customer":
						this.sendMarkerStringToClient("Update existing Customer");
						ArrayList<String> rawCustomersIn2 = (ArrayList<String>) this.listendForClientObjectsInfo();
						this.updateExistingCustomerInDatabase(rawCustomersIn2);
						this.sendMarkerStringToClient("Customer information updated successfully.");
						break;
					case "Remove customer from DB":
						this.sendMarkerStringToClient("Remove customer from DB");
						//NEED TO ERROR HANDLE THIS STATEMENT HERE AS WELL**************************************************
						int id2 = Integer.parseInt(this.listenForClientStringInfo());
						this.removeExistingCustomerFromDatabase(id2);
						this.sendMarkerStringToClient("Customer successfully removed from database.");
						break;
					case "Search for Customer by ID":
						this.sendMarkerStringToClient("Show Customers");
						//NEED TO ERROR HANDLE THIS STATEMENT HERE AS WELL**************************************************
						int id3 = Integer.parseInt(this.listenForClientStringInfo());
						String [] rawCustomer = this.getSingleCustomerFromDatabase(id3);
						this.buildCustomers(rawCustomer);
						this.writeCustomersToClient();
						break;
					case "Search for Customer by last name":
						this.sendMarkerStringToClient("Show Customers");
						String name4 = this.listenForClientStringInfo();
						String [] rawCustomer2 = this.getSingleCustomerFromDatabase(name4);
						this.buildCustomers(rawCustomer2);
						this.writeCustomersToClient();
						break;
					case "Search for all Customers by type":
						this.sendMarkerStringToClient("Show Customers");
						String name5 = this.listenForClientStringInfo();
						ArrayList<String[]> rawCustomersOut = this.getAllCustomersByTypeFromDatabase(name5);
						this.buildCustomers(rawCustomersOut);
						this.writeCustomersToClient();
						break;
					default:
						System.err.println("Invalid command from client.");
						break;
				}
				this.getShop().clearLists();
			}				
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			this.close();
			this.getServerController().close();
		}
	}

}
