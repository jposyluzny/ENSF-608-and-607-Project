package server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import server.Model.Shop;
import server.Model.Tool;
import sun.tools.tree.ThisExpression;

public class ServerModelController {
	
	private ServerController serverController;
	private DBController dataBaseController;
	private Shop shop;
	
	public ServerModelController (ServerController server, DBController dataBaseController, Shop shop) {
		this.setServerController(server);
		this.setDataBaseController(dataBaseController);
		this.setShop(shop);
		this.run();
	}

	//FOR TESTING ************************************************************************************************
	//
	//
	//
	//
	//
	//
	//
	//
//	public ServerModelController() {
//		shop = new Shop();
//	}
	
	public void run() {
		try {
		while (true) {
			System.out.println("At top of while loop");
			serverController.response = serverController.getSocketInStrings().readLine();
		if (serverController.response.equals("Tool")) {
			this.shop.buildTool("Dinko Hammer", 160, 4.20, 123456789, 42069, "Electrical", "THIS MFR REALLY HAS ELECTRICAL");
			this.shop.buildTool("Bupkis", 122, 0.01, 000000001, 234525, "Non-Electrical", null);
			serverController.getSocketOut().println("Tool");
			serverController.getSocketOutObjects().writeObject(this.getShop().getIm().getToolInventory().get(1));
		}
		if (serverController.response.equals("Customer")) {
			this.shop.buildCustomer(12314, "Boat", "Moat", "123 East Bimbleton", "T2S 1S7", "708 706 1111", "R");
			this.shop.buildCustomer(1143315, "Fucker", "Jones", "Cats ave", "123 sdfs", "1 800 go fuck yourself", "C");
			serverController.getSocketOut().println("Customer");
			serverController.getSocketOutObjects().writeObject(this.getShop().getCm().getCustomerList().get(0));
		}
		if (serverController.response.equals("Order")) {
			this.shop.buildOrderLine(1243521, 6006001, 32);
			this.shop.buildOrderLine(9281623, 1001008, 17);
			serverController.getSocketOut().println("Order");
			serverController.getSocketOutObjects().writeObject(this.getShop().getIm().getOrder());
		}
		if (serverController.response.equals("Quit")) {
			serverController.getSocketOut().println("Quit");
        	break;
		}
		}
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
//		this.shop.buildCustomer(12314, "Boat", "Moat", "123 East Bimbleton", "T2S 1S7", "708 706 1111", "R");
//		this.shop.buildCustomer(1143315, "Fucker", "Jones", "Cats ave", "123 sdfs", "1 800 go fuck yourself", "C");
//		for (server.Model.Customer i: shop.getCm().getCustomerList()) {
//			System.out.println(i);
//		}
//		
//		this.shop.buildSupplier("Dr Oof", "123141 Bumbo way", "780-776-1231", 214255, "International", 0.07);
//		this.shop.buildSupplier("Bongman", "placeholder ave", "800-800-8000", 11111111, "Local", 0.17);
//		for (server.Model.Supplier i: shop.getSm().getSupplierList()) {
//			System.out.println(i);
//		}
//		
//		this.shop.buildOrderLine(1243521, 6006001, 32);
//		this.shop.buildOrderLine(9281623, 1001008, 17);
//		System.out.println(shop.getIm().getOrder());
//		for (server.Model.OrderLine i: shop.getIm().getOrder().getOrderLines()) {
//			System.out.println(i);
//		}

	//
	//
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

	//FOR TESTING***********************************************************************************************
	//
	//
	//
	//
	//
	//
	//
	//
//	public static void main(String [] args) {
//		ServerModelController smc = new ServerModelController();
//		smc.run();
//	}
	
}
