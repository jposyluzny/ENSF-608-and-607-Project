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
