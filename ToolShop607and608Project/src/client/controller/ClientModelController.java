package client.controller;

import java.io.IOException;

import server.Model.Customer;
import server.Model.Order;
import server.Model.OrderLine;
import server.Model.Shop;
import server.Model.Tool;
import server.controller.DBController;
import server.controller.ServerController;

public class ClientModelController {
	
	private ClientController clientController;
	

	public ClientModelController (ClientController client) {
		this.clientController = client;
		this.run();
	}

	public void run() {
		boolean flag = true;
		try {
			while (true) {
				//TESTING ONLY
				System.out.println("Enter \"Tool\" or \"Customer\" or \"Order\" or \"Quit\".");
				clientController.response = clientController.stdIn.readLine();
				clientController.getSocketOut().println(clientController.response);
				clientController.response = clientController.getSocketInStrings().readLine();
				if (clientController.response.equals("Tool")) {
					Tool t = (Tool) clientController.getSocketInObjects().readObject();
					System.out.println(t);
				}
				if (clientController.response.equals("Customer")) {
					Customer t = (Customer) clientController.getSocketInObjects().readObject();
					System.out.println(t);
				}
				if (clientController.response.equals("Order")) {
					Order t = (Order) clientController.getSocketInObjects().readObject();
					for (OrderLine o: t.getOrderLines())
						System.out.println(o);
				}
				if (clientController.response.equals("Quit"))
					break;
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println(e.getStackTrace());
		}
	}

}
