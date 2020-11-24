package client.controller;

/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

import server.Model.*;

import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class will handle directing the data passed between the client and server. It will handle the logic calls and manipulation
 * required to make the client side of the program function properly.
 */
public class ClientModelController {
	
	private ClientController clientController;
	private InventoryViewController inventoryViewController;
	private CustomerViewController customerViewController;
	private Shop shop;
	
	/**
	 * Will set or construct the objects required for this object to function properly.
	 * @param client is the instance of the ClientController class that will be used to access the communication objects required
	 *  to communicate via the socket with the server.
	 * @param shop is the instance of the Model Shop class, that will direct logic calls and access the Model pacakage methods.
	 */
	public ClientModelController(ClientController client, Shop shop) {
		this.setClientController(client);
		this.setInventoryViewController(new InventoryViewController(this));
		this.setCustomerViewController(new CustomerViewController(this));
		this.setShop(shop);
	}
	
	/**
	 * This will read the String sent from server via the socket.
	 * @return is the String sent from the server
	 * @throws IOException is the exception thrown if the socket is closed for some reason.
	 */
	public String readMarkerStringsFromServer() throws IOException {
		return this.getClientController().getSocketInStrings().readLine();
	}

	/**
	 * This will read the objects read from the server, and cast them to Tool objects.
	 * @throws ClassNotFoundException will be thrown if the object sent is not castable to a Tool object.
	 * @throws IOException is the exception thrown if the socket is closed for some reason. 
	 * @throws EOFException is the exception thrown if an error with reading from the socket is encountered from an unforseen reason.
	 */
	public void readToolsFromServer() throws ClassNotFoundException, IOException, EOFException {
		Tool tool = (Tool) this.getClientController().getSocketInObjects().readObject();
		while (tool != null) {
			this.getShop().getIm().addToolToList(tool);
			tool = (Tool) this.getClientController().getSocketInObjects().readObject();
		}
	}
	
	/**
	 * Will update the InventoryGUI with Tools sent from the server.
	 */
	public void updateGUIWithTools() {
		this.getInventoryViewController().updateResultsAreaWithTools(this.getShop().getIm().getToolInventory());
	}
	
	/**
	 * Will update the InventoryGUI with the quantity of the Tool sent from the server.
	 */
	public void updateGUIWithQuantity() {
		this.getInventoryViewController().updateResultsAreaWithQuantity(this.getShop().getIm().getToolInventory());
	}
	
	/**
	 * Will update the InventoryGUI with a String sent from the server.
	 * @param input is the String sent from the server.
	 */
	public void printNotificationToInventoryGUI(String input) {
		this.getInventoryViewController().updateResultsAreaWithDecreaseQuantityNotification(input);
	}
	
	/**
	 * Will update the CustomerGUI with a String sent from the server.
	 * @param input is the String sent from the server.
	 */
	public void printNotificatinToCustomerGUI(String input) {
		this.getCustomerViewController().updateJList(input);	
	}
	
	/**
	 * This will read the objects read from the server, and cast them to Customer objects.
	 * @throws ClassNotFoundException will be thrown if the object sent is not castable to a Tool object.
	 * @throws IOException is the exception thrown if the socket is closed for some reason. 
	 */
	public void readCustomersFromServer() throws ClassNotFoundException, IOException {
		Customer customer = (Customer) this.getClientController().getSocketInObjects().readObject();
		while (customer != null) {
			this.getShop().getCm().addCustomerToList(customer);
			customer = (Customer) this.getClientController().getSocketInObjects().readObject();
		}
	}
	
	/**
	 * This will update the CustomerGUI with customer data fields. The Customers will be retrieved from the CustomerList held in
	 * the CustomerList class.
	 */
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
