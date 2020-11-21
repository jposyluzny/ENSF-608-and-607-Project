package server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import server.Model.Shop;
import server.Model.Tool;

public class ServerModelController {
	
	private ServerController serverController;
	private DBController dataBaseController;
	private Shop shop;
	
	public ServerModelController (ServerController server, DBController dataBaseController, Shop shop) {
		this.setServerController(server);
		this.setDataBaseController(dataBaseController);
		this.setShop(shop);
	}

	//TESTING *************************************************************************************************************
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//
	//THIS WILL NEED TO BE REFACTORED
	public void run() {
		//TESTING????
		String input = "";
		try {
			while (true) {
				//TODO: FIX REQUIREMENT FOR 2 STRING INPUTS, IT DOESNT WORK FOR LISTING ALL TOOLS
				//********************************************************************************************
				//********************************************************************************************
				input = serverController.getSocketInStrings().readLine();
				String name = serverController.getSocketInStrings().readLine();
//				int id = Integer.parseInt(serverController.getSocketInStrings().readLine());
				if (input.equals("List all Tools")) {
					serverController.getSocketOut().println("List all Tools");
					ArrayList<String[]> arr = dataBaseController.getIdbController().queryAllTools();
					for (String[] i: arr) {
						this.getShop().buildTool(Integer.parseInt(i[0]), i[1], Integer.parseInt(i[2]), Double.parseDouble(i[3]), Integer.parseInt(i[4]), i[5], i[6]);
					}
					serverController.getSocketOutObjects().writeObject(this.getShop().getIm().getToolInventory());
				}
				if (input.equals("Search Tool by Name")) {
					serverController.getSocketOut().println("Show Tool");
					String[] arr = dataBaseController.getIdbController().queryByName(name);
					this.getShop().buildTool(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]), Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), arr[5], arr[6]);
					serverController.getSocketOutObjects().writeObject(this.getShop().getIm().getToolInventory().get(0));
				}
//				if (input.equals("Search Tool by ID")) {
//					serverController.getSocketOut().println("Show Tool");
//					String[] arr = dataBaseController.getIdbController().queryById(id);
//					this.getShop().buildTool(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]), Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), arr[5], arr[6]);
//					serverController.getSocketOutObjects().writeObject(this.getShop().getIm().getToolInventory().get(0));
//				}
				//quantity by tool name
				if (input.equals("Check Quantity")) {
					serverController.getSocketOut().println("Check Quantity");
					String[] arr = dataBaseController.getIdbController().queryByName(name);
					this.getShop().buildTool(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]), Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), arr[5], arr[6]);
					serverController.getSocketOutObjects().writeObject(this.getShop().getIm().getToolInventory().get(0));
				}
				if (input.equals("Decrease Quantity")) {
					dataBaseController.getIdbController().decreaseQuantity(name);
				}
				
				this.getShop().clearLists();
				
				if (input.equals("Quit")) {
					serverController.getSocketOut().println("Quit");
					break;
				}
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		finally {
			this.getServerController().close();
		}
	}
	//
	//
	//
	//
//	public void exampleObjects() {
//		this.shop.buildTool("Dinko Hammer", 160, 4.20, 123456789, 42069, "Electrical", "THIS MFR REALLY HAS ELECTRICAL");
//		this.shop.buildTool("Bupkis", 122, 0.01, 000000001, 234525, "Non-Electrical", null);
//		this.shop.buildCustomer(12314, "Boat", "Moat", "123 East Bimbleton", "T2S 1S7", "708 706 1111", "R");
//		this.shop.buildCustomer(1143315, "Fucker", "Jones", "Cats ave", "123 sdfs", "1 800 go fuck yourself", "C");
//		this.shop.buildOrderLine(1243521, 6006001, 32);
//		this.shop.buildOrderLine(9281623, 1001008, 17);
//	}
	//
	//
	//
	//
	//
	//
	//

	public ServerController getServerController() {
		return serverController;
	}

	public void setServerController(ServerController serverController) {
		this.serverController = serverController;
	}

	public DBController getDataBaseController() {
		return dataBaseController;
	}

	public void setDataBaseController(DBController dataBaseController) {
		this.dataBaseController = dataBaseController;
	}
	
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

}
