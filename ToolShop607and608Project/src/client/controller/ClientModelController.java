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
	private CustomerViewController customerViewController;
	private Shop shop;
	
	public ClientModelController(ClientController client, Shop shop) {
		this.setClientController(client);
		this.setInventoryViewController(new InventoryViewController(this));
		this.setCustomerViewController(new CustomerViewController(this));
		this.setShop(shop);
	}

	//For testing, this will need to be refactored
	public void run() {
		System.out.println("Client running on PORT: " + this.getClientController().getPort());
		try {
			while (true) {
				String input = clientController.getSocketInStrings().readLine();
				if (input.equals("List all Tools")) {
					Tool tool = (Tool) this.getClientController().getSocketInObjects().readObject();
					while (tool != null) {
						this.getShop().getIm().addToolToList(tool);
						tool = (Tool) this.getClientController().getSocketInObjects().readObject();
					}
					this.getInventoryViewController().updateResultsAreaWithAllTools(this.getShop().getIm().getToolInventory());
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
					this.getCustomerViewController().updateJList(input);
				}
				if (input.equals("Update existing Customer")) {
					input = clientController.getSocketInStrings().readLine();
					this.getCustomerViewController().updateJList(input);
				}
				if (input.equals("Remove customer from DB")) {
					input = clientController.getSocketInStrings().readLine();
					this.getCustomerViewController().updateJList(input);
				}
				if (input.equals("Show single Customer")) {
					Customer customer = (Customer) clientController.getSocketInObjects().readObject();
					StringBuffer sb = new StringBuffer();
					sb.append(customer.getCustomerID() + " ");
					sb.append(customer.getFirstName() + " ");
					sb.append(customer.getLastName() + " ");
					sb.append(customer.getAddress() + " ");
					sb.append(customer.getPostalCode() + " ");
					sb.append(customer.getPhoneNumber() + " ");
					sb.append(customer.getType() + " ");
					this.getCustomerViewController().updateJList(sb.toString());
				}
				if (input.equals("Show all Customers of type")) {
					Customer customer = (Customer) this.getClientController().getSocketInObjects().readObject();
					while (customer != null) {
						this.getShop().getCm().addCustomerToList(customer);
						customer = (Customer) this.getClientController().getSocketInObjects().readObject();
					}
					ArrayList<String> arr = new ArrayList<String> ();
					for (Customer i: this.getShop().getCm().getCustomerList()) {
						System.out.println(i.getFirstName() + " " + i.getLastName());
						StringBuffer sb = new StringBuffer();
						sb.append(Integer.toString(i.getCustomerID()) + " ");
						sb.append(i.getFirstName() + " ");
						sb.append(i.getLastName() + " ");
						sb.append(i.getAddress() + " ");
						sb.append(i.getPostalCode() + " ");
						sb.append(i.getPhoneNumber() + " ");
						sb.append(i.getType() + " ");
						arr.add(sb.toString());
					}
					this.getCustomerViewController().updateJList(arr);
				}	
				
				this.getShop().clearLists();
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

	public CustomerViewController getCustomerViewController() {
		return customerViewController;
	}

	public void setCustomerViewController(CustomerViewController customerViewController) {
		this.customerViewController = customerViewController;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
