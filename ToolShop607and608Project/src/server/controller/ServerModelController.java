package server.controller;

import server.Model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ServerModelController {
	
	private ServerController serverController;
	private DBController dataBaseController;
	private Shop shop;
    private BufferedReader socketInStrings;
    private ObjectInputStream socketInObjects;
    private ObjectOutputStream socketOutObjects;
    private PrintWriter socketOutStrings;
	
	public ServerModelController (ServerController server, DBController dataBaseController, Shop shop) {
		try {
			this.setServerController(server);
			this.setDataBaseController(dataBaseController);
			this.setShop(shop);
			this.setSocketInStrings(new BufferedReader(new InputStreamReader(this.getServerController().getsSocket().getInputStream())));
			this.setSocketOutObjects(new ObjectOutputStream(this.getServerController().getsSocket().getOutputStream()));
			this.setSocketInObjects(new ObjectInputStream(this.getServerController().getsSocket().getInputStream()));
			this.setSocketOutStrings(new PrintWriter(this.getServerController().getsSocket().getOutputStream(), true));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void connectToDatabases() {
		System.out.println("Trying to connect to databases.");
		this.getDataBaseController().getIdbController().connect();
		this.getDataBaseController().getCdbController().connect();
		this.getDataBaseController().getOdbController().connect();
		System.out.println("Databases connected to successfully.");
	}
	
	public String listenForStringMarkers() throws IOException {
		return this.getSocketInStrings().readLine();
	}
	
	public String listenForClientStringInfo() throws IOException {
		return this.getSocketInStrings().readLine();
	}
	
	public Object listendForClientObjectsInfo() throws IOException, ClassNotFoundException {
		return this.getSocketInObjects().readObject();
	}
	
	public void sendMarkerStringToClient(String output) {
		this.getSocketOutStrings().println(output);
	}
	
	public ArrayList<String []> getAllToolsFromDatabase() {
		return this.getDataBaseController().getIdbController().queryAllTools();
	}
	
	public String[] getSingleToolFromDatabase(String name) {
		return this.getDataBaseController().getIdbController().queryByName(name);
	}
	
	public String[] getSingleToolFromDatabase(int id) {
		return this.getDataBaseController().getIdbController().queryById(id);
	}
	
	public void decreaseToolQuantity(String name) {
		this.getDataBaseController().getIdbController().decreaseQuantity(name);
		
	}
	
	public void orderMoreTools(String name) {
		String [] arr = this.getDataBaseController().getIdbController().queryByName(name);
		this.buildTools(arr);
		this.getShop().buildOrderLine();
		this.insertOrderIntoDatabase();
		this.insertOrderLineIntoDatabase();
		this.updateToolQuantityInDatabase(name, this.getShop().getIm().getToolInventory().get(0).getQuantity());
	}
	
	public void updateToolQuantityInDatabase(String name, int quantity) {
		this.getDataBaseController().getIdbController().updateQuantity(name, quantity);
	}
	
	public void insertOrderIntoDatabase() {
		this.getDataBaseController().getOdbController().queryOrderTable(this.getShop().getIm().getOrder().getOrderID(), 
																		this.getShop().getIm().getOrder().getDate());
	}
	
	public void insertOrderLineIntoDatabase() {
		this.getDataBaseController().getOdbController().queryOrderLineTable(this.getShop().getIm().getOrder().getOrderID(),
				this.getShop().getIm().getOrder().getOrderLines().get(0).getToolID(),
				this.getShop().getIm().getOrder().getOrderLines().get(0).getSupplierID(),
				this.getShop().getIm().getOrder().getOrderLines().get(0).getOrderQuantity());
	}
 	
	public void buildTools(String[] rawTool) {
		this.getShop().buildTool(Integer.parseInt(rawTool[0]), rawTool[1], Integer.parseInt(rawTool[2]), 
								Double.parseDouble(rawTool[3]), Integer.parseInt(rawTool[4]), rawTool[5], rawTool[6]);
	}
	
	public void buildTools(ArrayList<String[]> rawTools) {
		for (String[] i: rawTools) {
			this.getShop().buildTool(Integer.parseInt(i[0]), i[1], Integer.parseInt(i[2]), Double.parseDouble(i[3]), 
									 Integer.parseInt(i[4]), i[5], i[6]);
		}
	}
	
	public void writeToolsToClient() throws IOException {
		for (Tool tool: this.getShop().getIm().getToolInventory())
			this.getSocketOutObjects().writeObject(tool);
		this.getSocketOutObjects().writeObject(null);
	}
	
	public void addNewCustomerToDatabase(ArrayList<String> rawCustomer) {
		this.getDataBaseController().getCdbController().addNewCustomer(rawCustomer.get(1), rawCustomer.get(2), 
							  rawCustomer.get(3), rawCustomer.get(4), rawCustomer.get(5), rawCustomer.get(6));
	}
	
	public void updateExistingCustomerInDatabase(ArrayList<String> rawCustomer) {
		this.getDataBaseController().getCdbController().updateCustomerInfo(Integer.parseInt(rawCustomer.get(0)), 
			rawCustomer.get(1), rawCustomer.get(2), rawCustomer.get(3), rawCustomer.get(4), rawCustomer.get(5));
	}
	
	public void removeExistingCustomerFromDatabase(int id) {
		this.getDataBaseController().getCdbController().removeCustomer(id);
	}
	
	public String[] getSingleCustomerFromDatabase(int id) {
		return this.getDataBaseController().getCdbController().queryById(id);
	}
	
	public String[] getSingleCustomerFromDatabase(String name) {
		return this.getDataBaseController().getCdbController().queryByName(name);
	}
	
	public ArrayList<String[]> getAllCustomersByTypeFromDatabase(String type) {
		return this.getDataBaseController().getCdbController().queryCustomerTypes(type);
	}
	
	//ERROR HANDLING HERE
	public void buildCustomers(String[] rawCustomer) {
		this.getShop().buildCustomer(Integer.parseInt(rawCustomer[0]), rawCustomer[1], rawCustomer[2], rawCustomer[3], 
													  rawCustomer[4], rawCustomer[5], rawCustomer[6]);
	}
		
	//ERROR HANDLING HERE
	public void buildCustomers(ArrayList<String[]> rawCustomers) {
		for (String[] i: rawCustomers)
			this.getShop().buildCustomer(Integer.parseInt(i[0]), i[1], i[2], i[3], i[4], i[5], i[6]);
	}
	
	public void writeCustomersToClient() throws IOException {
		for (Customer customer: this.getShop().getCm().getCustomerList())
			this.getSocketOutObjects().writeObject(customer);
		this.getSocketOutObjects().writeObject(null);
	}
	
	public void close () {
		try {
    		socketInStrings.close();
    		socketInObjects.close();
    		socketOutObjects.close();
    		socketOutStrings.close();
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

	public void setSocketInStrings(BufferedReader socketInStrings) {
		this.socketInStrings = socketInStrings;
	}

	public ObjectOutputStream getSocketOutObjects() {
		return socketOutObjects;
	}

	public void setSocketOutObjects(ObjectOutputStream socketOutObjects) {
		this.socketOutObjects = socketOutObjects;
	}

	public PrintWriter getSocketOutStrings() {
		return socketOutStrings;
	}

	public void setSocketOutStrings(PrintWriter socketOutStrings) {
		this.socketOutStrings = socketOutStrings;
	}

	public ObjectInputStream getSocketInObjects() {
		return socketInObjects;
	}

	public void setSocketInObjects(ObjectInputStream socketInObjects) {
		this.socketInObjects = socketInObjects;
	}

}
