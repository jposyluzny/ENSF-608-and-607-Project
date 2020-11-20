package server.controller;

import server.Model.Shop;

public class ServerModelController {
	
	private ServerController serverController;
	private DBController dataBaseController;
	private Shop shop;
	
	public ServerModelController (ServerController server, DBController dataBaseController, Shop shop) {
		this.setServerController(server);
		this.setDataBaseController(dataBaseController);
		this.setShop(shop);
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
	public ServerModelController() {
		shop = new Shop();
	}
	
	public void run() {
		this.shop.buildTool("Dinko Hammer", 160, 4.20, 123456789, 42069, "Electrical", "THIS MFR REALLY HAS ELECTRICAL");
		this.shop.buildTool("Bupkis", 122, 0.01, 000000001, 234525, "Non-Electrical", null);
		for (server.Model.Tool i: shop.getIm().getToolInventory()) {
			System.out.println(i);
		}
		
		this.shop.buildCustomer(12314, "Boat", "Moat", "123 East Bimbleton", "T2S 1S7", "708 706 1111", "R");
		this.shop.buildCustomer(1143315, "Fucker", "Jones", "Cats ave", "123 sdfs", "1 800 go fuck yourself", "C");
		for (server.Model.Customer i: shop.getCm().getCustomerList()) {
			System.out.println(i);
		}
		
		this.shop.buildSupplier("Dr Oof", "123141 Bumbo way", "780-776-1231", 214255, "International", 0.07);
		this.shop.buildSupplier("Bongman", "placeholder ave", "800-800-8000", 11111111, "Local", 0.17);
		for (server.Model.Supplier i: shop.getSm().getSupplierList()) {
			System.out.println(i);
		}
		
		this.shop.buildOrderLine(1243521, 6006001, 32);
		this.shop.buildOrderLine(9281623, 1001008, 17);
		System.out.println(shop.getIm().getOrder());
		for (server.Model.OrderLine i: shop.getIm().getOrder().getOrderLines()) {
			System.out.println(i);
		}
		
		System.out.println("\nEnd of testing");
	}
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
	public static void main(String [] args) {
		ServerModelController smc = new ServerModelController();
		smc.run();
	}
	
}
