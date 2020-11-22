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
		try {
			while (true) {
				String input = serverController.getSocketInStrings().readLine();
				if (input.equals("List all Tools")) {
					serverController.getSocketOut().println("List all Tools");
					ArrayList<String[]> arrL = new ArrayList<String[]> (dataBaseController.getIdbController().queryAllTools());
					for (String[] i: arrL)
						this.getShop().buildTool(Integer.parseInt(i[0]), i[1], Integer.parseInt(i[2]), Double.parseDouble(i[3]), Integer.parseInt(i[4]), i[5], i[6]);
					serverController.getSocketOutObjects().writeObject(this.getShop().getIm().getToolInventory());
				}
				
				if (input.equals("Search Tool by Name")) {
					String name = serverController.getSocketInStrings().readLine();
					serverController.getSocketOut().println("Show Tool");
					String[] arr = dataBaseController.getIdbController().queryByName(name);
					this.getShop().buildTool(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]), Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), arr[5], arr[6]);
					serverController.getSocketOutObjects().writeObject(this.getShop().getIm().getToolInventory().get(0));
				}
				
				if (input.equals("Search Tool by ID")) {
					int id = Integer.parseInt(serverController.getSocketInStrings().readLine());
					serverController.getSocketOut().println("Show Tool");
					String[] arr = dataBaseController.getIdbController().queryById(id);
					this.getShop().buildTool(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]), Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), arr[5], arr[6]);
					serverController.getSocketOutObjects().writeObject(this.getShop().getIm().getToolInventory().get(0));
				}
				
				if (input.equals("Check Quantity")) {
					String name = serverController.getSocketInStrings().readLine();
					serverController.getSocketOut().println("Check Quantity");
					String[] arr = dataBaseController.getIdbController().queryByName(name);
					this.getShop().buildTool(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]), Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), arr[5], arr[6]);
					serverController.getSocketOutObjects().writeObject(this.getShop().getIm().getToolInventory().get(0));
				}
				
				if (input.equals("Decrease Quantity")) {
					String name = serverController.getSocketInStrings().readLine();
					dataBaseController.getIdbController().decreaseQuantity(name);
					serverController.getSocketOut().println("Decrease Quantity");
					serverController.getSocketOut().println("Tool quantity decreased successfully.");
				}
				
				if (input.equals("Add new Customer")) {
//					...
				}
				
				if (input.equals("Update existing Customer")) {
//					...
				}
				
				if (input.equals("Remove customer from DB")) {
					int id = Integer.parseInt(serverController.getSocketInStrings().readLine());
					this.getDataBaseController().getCdbController().removeCustomer(id);
					serverController.getSocketOut().println("Remove customer from DB");
					serverController.getSocketOut().println("Customer successfully removed from database.");
				}
				
				if (input.equals("Search for Customer by ID")) {
					int id = Integer.parseInt(serverController.getSocketInStrings().readLine());
					serverController.getSocketOut().println("Show single Customer");
					String[] arr = this.getDataBaseController().getCdbController().queryById(id);
					this.getShop().buildCustomer(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
					serverController.getSocketOutObjects().writeObject(this.getShop().getCm().getCustomerList().get(0));
				}
				
				if (input.equals("Search for Customer by last name")) {
					String name = serverController.getSocketInStrings().readLine();
					serverController.getSocketOut().println("Show single Customer");
					String[] arr = this.getDataBaseController().getCdbController().queryByName(name);
					this.getShop().buildCustomer(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
					serverController.getSocketOutObjects().writeObject(this.getShop().getCm().getCustomerList().get(0));
				}
				
				if (input.equals("Search for all Customers by type")) {
					String type = serverController.getSocketInStrings().readLine();
					serverController.getSocketOut().println("Show all Customers of type");
					ArrayList<String[]> arr = new ArrayList<String[]> (this.getDataBaseController().getCdbController().queryCustomerTypes(type));
					for (String[] i: arr)
						this.getShop().getCm().buildCustomer(Integer.parseInt(i[0]), i[1], i[2], i[3], i[4], i[5], i[6]);
					serverController.getSocketOutObjects().writeObject(this.getShop().getCm().getCustomerList());
				}
				
				this.getShop().clearLists();
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
