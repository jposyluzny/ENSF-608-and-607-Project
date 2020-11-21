package client.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import server.Model.*;

public class ClientModelController {
	
	private ClientController clientController;
	
	//TESTING
	private BufferedReader stdIn;
	
	public ClientModelController (ClientController client) {
		this.clientController = client;
		//Testing
		stdIn = new BufferedReader(new InputStreamReader(System.in));
	}
	
	//For testing, this will need to be refactored
	public void run() {
		//FOR TESTING ONLY
	    String input = "";
		try {
			while (true) {
				System.out.println("Enter \"List all Tools\", \"Search Tool by Name\" or \"Search Tool by ID\" "
						+"or \"Check Quantity\" or \"Decrease Quantity\", or type \"Quit\".");
				input = stdIn.readLine();
				System.out.println("Input Tool Name");
				String name = stdIn.readLine();
				clientController.getSocketOut().println(input);
				clientController.getSocketOut().println(name);
				input = clientController.getSocketInStrings().readLine();
				if (input.equals("List all Tools")) {
					ArrayList<Tool> toolList = (ArrayList<Tool>) clientController.getSocketInObjects().readObject();
					for (Tool t: toolList)
						System.out.println(t);
				}
				if (input.equals("Show Tool")) {
					Tool tool = (Tool) clientController.getSocketInObjects().readObject();
					System.out.println(tool);
				}
				if (input.equals("Check Quantity")) {
					Tool tool = (Tool) clientController.getSocketInObjects().readObject();
					System.out.println("".format("Quantity of tool: %s is %d", tool.getToolName(), tool.getQuantity()));
				}
				//TODO: The customer functionality will need to go here as well.
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
	
	public BufferedReader getStdIn() {
		return stdIn;
	}

	public void setStdIn(BufferedReader stdIn) {
		this.stdIn = stdIn;
	}

}
