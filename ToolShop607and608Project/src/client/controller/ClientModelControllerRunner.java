package client.controller;

/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

import java.io.IOException;
import server.Model.Shop;

/**
 * This class will be the subclass of the ClientModelController class. It will have access to all of its methods, but will exclusively
 * handle the communication loop required to continually pass/read data with the server.
 */
public class ClientModelControllerRunner extends ClientModelController {

	/**
	 * This will also make a call to the ClientModelController superclass to be constructed.
	 * @param client is the instance of the ClientController class required to communicate with the server.
	 * @param shop is the instance of the Shop class required to use the Model package.
	 */
	public ClientModelControllerRunner(ClientController client, Shop shop) {
		super(client, shop);
	}

	/**
	 * This method will continually handle the communication loop required to read information from the client. It will also call the
	 * proper methods required to manipulate the data required by the user. The Strings sent by the server will direct this loop to 
	 * call the proper methods based on the String sent.
	 */
	public void run() {
		System.out.println("Client running on PORT: " + this.getClientController().getPort());
		while (true) {
			try {
				this.getShop().clearLists();

				String input = this.readMarkerStringsFromServer();
				switch (input) {
				case "List all Tools":
					this.readToolsFromServer();
					this.updateGUIWithTools();
					break;
				case "Show Tool":
					this.readToolsFromServer();
					this.updateGUIWithTools();
					break;
				case "Check Quantity":
					this.readToolsFromServer();
					this.updateGUIWithQuantity();
					break;
				case "Decrease Quantity":
					input = this.readMarkerStringsFromServer();
					this.printNotificationToInventoryGUI(input);
					break;
				case "Add new Customer":
					input = this.readMarkerStringsFromServer();
					this.printNotificatinToCustomerGUI(input);
					break;
				case "Update existing Customer":
					input = this.readMarkerStringsFromServer();
					this.printNotificatinToCustomerGUI(input);
					break;
				case "Remove customer from DB":
					input = this.readMarkerStringsFromServer();
					this.printNotificatinToCustomerGUI(input);
					break;
				case "Show Customers":
					this.readCustomersFromServer();
					this.updateGUIWithCustomer();
					break;
				case "Wrong input":
					input = this.readMarkerStringsFromServer();
					this.printNotificationToInventoryGUI(input);
					this.printNotificatinToCustomerGUI(input);
				default:
					System.err.println("Invalid command from client.");
					break;
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
				break;
			}
		}				
		this.getClientController().close();
	}
	
}
