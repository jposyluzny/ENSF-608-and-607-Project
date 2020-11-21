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
	    String st = "";
		try {
			while (true) {
				input = clientController.getSocketInStrings().readLine();
				if (input.equals("List all Tools")) {
					ArrayList<Tool> toolList = (ArrayList<Tool>) clientController.getSocketInObjects().readObject();
					for (Tool t: toolList) {
						st += t.toString() + "\n";
					}
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
	
	public ClientController getClientController() {
		return this.clientController;
	}
	
	public void setClientController(ClientController clientController) {
		this.clientController = clientController;
	}
	
	public BufferedReader getStdIn() {
		return stdIn;
	}

	public void setStdIn(BufferedReader stdIn) {
		this.stdIn = stdIn;
	}

}
