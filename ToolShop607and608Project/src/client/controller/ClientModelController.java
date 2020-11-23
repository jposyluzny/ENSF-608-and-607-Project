package client.controller;

import server.Model.*;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;

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
	
	public String readMarkerStringsFromServer() throws IOException {
		return this.getClientController().getSocketInStrings().readLine();
	}

	public void readToolsFromServer() throws ClassNotFoundException, IOException, EOFException {
		Tool tool = (Tool) this.getClientController().getSocketInObjects().readObject();
		while (tool != null) {
			this.getShop().getIm().addToolToList(tool);
			tool = (Tool) this.getClientController().getSocketInObjects().readObject();
		}
	}
	
	public void updateGUIWithTools() {
		this.getInventoryViewController().updateResultsAreaWithTools(this.getShop().getIm().getToolInventory());
	}
	
	public void updateGUIWithQuantity() {
		this.getInventoryViewController().updateResultsAreaWithQuantity(this.getShop().getIm().getToolInventory());
	}
	
	public void printNotificationToInventoryGUI(String input) {
		this.getInventoryViewController().updateResultsAreaWithDecreaseQuantityNotification(input);
	}
	
	public void printNotificatinToCustomerGUI(String input) {
		this.getCustomerViewController().updateJList(input);	
	}
	
	public void readCustomersFromServer() throws ClassNotFoundException, IOException {
		Customer customer = (Customer) this.getClientController().getSocketInObjects().readObject();
		while (customer != null) {
			this.getShop().getCm().addCustomerToList(customer);
			customer = (Customer) this.getClientController().getSocketInObjects().readObject();
		}
	}
	
	public void updateGUIWithCustomer() {
		ArrayList<String> customers = new ArrayList<String> ();
		for (Customer i: this.getShop().getCm().getCustomerList()) {
			StringBuffer sb = new StringBuffer();
			sb.append(Integer.toString(i.getCustomerID()) + " ");
			sb.append(i.getFirstName() + " ");
			sb.append(i.getLastName() + " ");
			sb.append(i.getAddress() + " ");
			sb.append(i.getPostalCode() + " ");
			sb.append(i.getPhoneNumber() + " ");
			sb.append(i.getType() + " ");
			customers.add(sb.toString());
		}
		this.getCustomerViewController().updateJList(customers);
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
