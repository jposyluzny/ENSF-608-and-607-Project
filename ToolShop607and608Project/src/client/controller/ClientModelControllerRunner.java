package client.controller;

import java.io.IOException;
import server.Model.Shop;

public class ClientModelControllerRunner extends ClientModelController implements Runnable {

	public ClientModelControllerRunner(ClientController client, Shop shop) {
		super(client, shop);
	}

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
