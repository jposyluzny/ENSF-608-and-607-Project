package client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import client.view.InventoryGUI;
import server.Model.*;

public class ClientModelController {
	
	private ClientController clientController;
	private InventoryViewController inventoryViewController;
	
	public ClientModelController(ClientController client) {
		this.setClientController(client);
		this.setInventoryViewController(new InventoryViewController(this));
	}

	//For testing, this will need to be refactored
	//TODO: BUG WHEN RUNNING LIST ALL TOOLS METHOD. ALL OTHER METHODS CORRECTLY SHOW UPDATED QUANTITY AFTER DECREASING QUANTITY, BUT
	//WHEN DISPLAYING ALL TOOLS, THE QUANTITY DISPLAYED IS THE OLD QUANTITY BEFORE QUANTITY WAS CHANGED. NEED TO FIX.
	public void run() {
		try {
			while (true) {
				String input = clientController.getSocketInStrings().readLine();
				if (input.equals("List all Tools")) {
					ArrayList<Tool> toolList = new ArrayList<Tool> ((ArrayList<Tool>) clientController.getSocketInObjects().readObject());
					this.getInventoryViewController().updateResultsAreaWithAllTools(toolList);
				}
				if (input.equals("Show Tool")) {
					Tool tool = (Tool) clientController.getSocketInObjects().readObject();
					this.getInventoryViewController().updateResultsAreaWithSingleTool(tool);
				}
				
				if (input.equals("Check Quantity")) {
					Tool tool = (Tool) clientController.getSocketInObjects().readObject();
					this.getInventoryViewController().updateResultsAreaWithQuantity(tool);
				}
				if (input.equals("Decrease Quantity")) {
					input = clientController.getSocketInStrings().readLine();
					this.getInventoryViewController().updateResultsAreaWithDecreaseQuantityNotification(input);
				}
				if (input.equals("Add new Customer")) {
					input = clientController.getSocketInStrings().readLine();
					//print "Customer has been successfully added to DB" to textarea
//					this.getCustomerViewController().TBD...
				}
				if (input.equals("Update existing Customer")) {
					Customer customer = (Customer) clientController.getSocketInObjects().readObject();
					//display updated customer object  fields to GUI areas
//					this.getCustomerViewController().TBD...
				}
				if (input.equals("Remove customer from DB")) {
					input = clientController.getSocketInStrings().readLine();
					//print input to textarea
//					this.getCustomerViewController().TBD...
				}
				if (input.equals("Show single Customer")) {
					Customer customer = (Customer) clientController.getSocketInObjects().readObject();
					//call method to display customer to textarea
//					this.getCustomerViewController().TBD...
				}
				if (input.equals("Show all Customers of type")) {
					ArrayList<Customer> customerList = new ArrayList<Customer> ((ArrayList<Customer>) clientController.getSocketInObjects().readObject());
					//call method to diplay list of customer to textarea
//					this.getCustomerViewController().TBD...
				}	
				if (input.equals("Quit"))
					break;
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			this.clientController.close();
		}
	}
	
	public ClientController getClientController() {
		return this.clientController;
	}
	
	public void setClientController(ClientController clientController) {
		this.clientController = clientController;
	}

	public InventoryViewController getInventoryViewController() {
		return inventoryViewController;
	}

	public void setInventoryViewController(InventoryViewController inventoryViewController) {
		this.inventoryViewController = inventoryViewController;
	}

}
