package server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import server.Model.Customer;
import server.Model.Shop;
import server.Model.Tool;

public class ServerModelController implements Runnable {
	
	private ServerController serverController;
	private DBController dataBaseController;
	private Shop shop;
    private BufferedReader socketInStrings;
    private ObjectInputStream socketInObjects;
    private ObjectOutputStream socketOutObjects;
    private PrintWriter socketOut;
	
	public ServerModelController (ServerController server, DBController dataBaseController, Shop shop) {
		try {
			this.setServerController(server);
			this.setDataBaseController(dataBaseController);
			this.setShop(shop);
			socketInStrings = new BufferedReader(new InputStreamReader(this.getServerController().getsSocket().getInputStream()));
			socketOutObjects = new ObjectOutputStream(this.getServerController().getsSocket().getOutputStream());
			socketInObjects = new ObjectInputStream(this.getServerController().getsSocket().getInputStream());
			socketOut = new PrintWriter(this.getServerController().getsSocket().getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			System.out.println("Trying to connect to databases.");
			this.getDataBaseController().getIdbController().connect();
			this.getDataBaseController().getCdbController().connect();
//			this.getDataBaseController().getOdbController().connect();
			System.out.println("Databases connected to successfully.");
			while (true) {
				String input = this.getSocketInStrings().readLine();
//				System.out.println(input);
				if (input.equals("List all Tools")) {
					this.getSocketOut().println("List all Tools");
					ArrayList<String[]> arrL = new ArrayList<String[]> (dataBaseController.getIdbController().queryAllTools());
					for (String[] i: arrL)
						this.getShop().buildTool(Integer.parseInt(i[0]), i[1], Integer.parseInt(i[2]), Double.parseDouble(i[3]), Integer.parseInt(i[4]), i[5], i[6]);
					for (Tool tool: this.getShop().getIm().getToolInventory()) {
						this.getSocketOutObjects().writeObject(tool);
					}
					this.getSocketOutObjects().writeObject(null);
				}
				
				if (input.equals("Search Tool by Name")) {
					String name = this.getSocketInStrings().readLine();
					this.getSocketOut().println("Show Tool");
					String[] arr = dataBaseController.getIdbController().queryByName(name);
					this.getShop().buildTool(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]), Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), arr[5], arr[6]);
					this.getSocketOutObjects().writeObject(this.getShop().getIm().getToolInventory().get(0));
				}
				
				if (input.equals("Search Tool by ID")) {
					int id = Integer.parseInt(this.getSocketInStrings().readLine());
					this.getSocketOut().println("Show Tool");
					String[] arr = dataBaseController.getIdbController().queryById(id);
					this.getShop().buildTool(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]), Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), arr[5], arr[6]);
					this.getSocketOutObjects().writeObject(this.getShop().getIm().getToolInventory().get(0));
				}
				
				if (input.equals("Check Quantity")) {
					String name = this.getSocketInStrings().readLine();
					this.getSocketOut().println("Check Quantity");
					String[] arr = dataBaseController.getIdbController().queryByName(name);
					this.getShop().buildTool(Integer.parseInt(arr[0]), arr[1], Integer.parseInt(arr[2]), Double.parseDouble(arr[3]), Integer.parseInt(arr[4]), arr[5], arr[6]);
					this.getSocketOutObjects().writeObject(this.getShop().getIm().getToolInventory().get(0));
				}
				
				if (input.equals("Decrease Quantity")) {
					String name = this.getSocketInStrings().readLine();
					dataBaseController.getIdbController().decreaseQuantity(name);
					this.getSocketOut().println("Decrease Quantity");
					this.getSocketOut().println("Tool quantity decreased successfully.");
				}
				
				if (input.equals("Add new Customer")) {
					ArrayList<String> newCustomerInfoList = (ArrayList<String>) (this.getSocketInObjects().readObject());
					this.getDataBaseController().getCdbController().addNewCustomer(newCustomerInfoList.get(1), newCustomerInfoList.get(2), newCustomerInfoList.get(3), newCustomerInfoList.get(4), newCustomerInfoList.get(5), newCustomerInfoList.get(6));
					this.getSocketOut().println("Add new Customer");
					this.getSocketOut().println("Customer information added to database successfully.");
				}
				
				if (input.equals("Update existing Customer")) {
					ArrayList<String> customerInfoList = (ArrayList<String>) (this.getSocketInObjects().readObject());
					this.getDataBaseController().getCdbController().updateCustomerInfo(Integer.parseInt(customerInfoList.get(0)), customerInfoList.get(1), customerInfoList.get(2), customerInfoList.get(3), customerInfoList.get(4), customerInfoList.get(5));
					this.getSocketOut().println("Update existing Customer");
					this.getSocketOut().println("Customer information updated successfully.");
				}
				
				if (input.equals("Remove customer from DB")) {
					int id = Integer.parseInt(this.getSocketInStrings().readLine());
					this.getDataBaseController().getCdbController().removeCustomer(id);
					this.getSocketOut().println("Remove customer from DB");
					this.getSocketOut().println("Customer successfully removed from database.");
				}
				
				if (input.equals("Search for Customer by ID")) {
					int id = Integer.parseInt(this.getSocketInStrings().readLine());
					this.getSocketOut().println("Show single Customer");
					String[] arr = this.getDataBaseController().getCdbController().queryById(id);
					this.getShop().buildCustomer(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
					this.getSocketOutObjects().writeObject(this.getShop().getCm().getCustomerList().get(0));
				}
				
				if (input.equals("Search for Customer by last name")) {
					String name = this.getSocketInStrings().readLine();
					this.getSocketOut().println("Show single Customer");
					String[] arr = this.getDataBaseController().getCdbController().queryByName(name);
					this.getShop().buildCustomer(Integer.parseInt(arr[0]), arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
					this.getSocketOutObjects().writeObject(this.getShop().getCm().getCustomerList().get(0));
				}
				
				if (input.equals("Search for all Customers by type")) {
					String type = this.getSocketInStrings().readLine();
					this.getSocketOut().println("Show all Customers of type");
					ArrayList<String[]> arr = new ArrayList<String[]> (this.getDataBaseController().getCdbController().queryCustomerTypes(type));
					for (String[] i: arr) {
						this.getShop().getCm().buildCustomer(Integer.parseInt(i[0]), i[1], i[2], i[3], i[4], i[5], i[6]);
					}
					for (Customer cust: this.getShop().getCm().getCustomerList()) {
						this.getSocketOutObjects().writeObject(cust);
					}
					this.getSocketOutObjects().writeObject(null);
				}
				
				this.getShop().clearLists();
			}
		} catch (IOException | ClassNotFoundException e1) {
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

	
	public void close () {
		try {
    		socketInStrings.close();
    		socketOutObjects.close();
    		socketOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	
    public BufferedReader getSocketInStrings() {
    	return socketInStrings;
    }

	public ObjectOutputStream getSocketOutObjects() {
		return socketOutObjects;
	}

	public void setSocketOutObjects(ObjectOutputStream socketOutObjects) {
		this.socketOutObjects = socketOutObjects;
	}

	public PrintWriter getSocketOut() {
		return socketOut;
	}

	public void setSocketOut(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}

	public ObjectInputStream getSocketInObjects() {
		return socketInObjects;
	}

	public void setSocketInObjects(ObjectInputStream socketInObjects) {
		this.socketInObjects = socketInObjects;
	}

}
