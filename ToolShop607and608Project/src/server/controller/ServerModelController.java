/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.controller;
import server.Model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class is responsible for managing the communication between the Model and Controller packages.
 */
public class ServerModelController {
	
	private ServerController serverController;
	private DBController dataBaseController;
	private Shop shop;
    private BufferedReader socketInStrings;
    private ObjectInputStream socketInObjects;
    private ObjectOutputStream socketOutObjects;
    private PrintWriter socketOutStrings;
	
    /**
     * The ServerModelController constructor will set the input parameters, and set each socket with their respective class objects.
     * @param server is the ServerController object
     * @param dataBaseController is the DBController object
     * @param shop is the Shop object
     */
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
	
	/**
	 * The connectToDatabases() method will connect to each SQL database.
	 */
	public void connectToDatabases() {
		System.out.println("Trying to connect to databases.");
		this.getDataBaseController().getIdbController().connect();
		this.getDataBaseController().getCdbController().connect();
		this.getDataBaseController().getOdbController().connect();
		System.out.println("Databases connected to successfully.");
	}
	
	/**
	 * The listenForStringMarkers() method will be called in the ServerModelControllerRunner class, and it will listen for commands
	 * from the client side.
	 * @return the command received
	 * @throws IOException
	 */
	public String listenForStringMarkers() throws IOException {
		return this.getSocketInStrings().readLine();
	}
	
	/**
	 * The listenForClientString() method will be called in the ServerModelControllerRunner class, and it will listen for the search
	 * parameters from the client side.
	 * @return the search parameters received
	 * @throws IOException
	 */
	public String listenForClientStringInfo() throws IOException {
		return this.getSocketInStrings().readLine();
	}
	
	/**
	 * The listenForClientObjectsInfo() method will be called in the ServerModelControllerRunner class, and it will listen for objects
	 * sent from the client. 
	 * @return the object received
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object listenForClientObjectsInfo() throws IOException, ClassNotFoundException {
		return this.getSocketInObjects().readObject();
	}
	
	/**
	 * The sendMarkerStringToClient() object will be called in the ServerModelControllerRUnner class, and it will send commands to the
	 * client side.
	 * @param output is the command to send to the client
	 */
	public void sendMarkerStringToClient(String output) {
		this.getSocketOutStrings().println(output);
	}
	
	/**
	 * The getAllToolsFromDatabase() method will be called in the ServerModelControllerRunner class, and it will run the queryAllTools() method
	 * from the InventoryDBController to find all tools in the database.
	 * @return the ArrayList of tools
	 */
	public ArrayList<String []> getAllToolsFromDatabase() {
		return this.getDataBaseController().getIdbController().queryAllTools();
	}
	
	/**
	 * This getSingleToolFromDatabase() method will be called in the ServerModelControllerRunner class, and it will run the queryByName() method
	 * from the InventoryDBController to find a tool by name in the database.
	 * @return the tool in an array of strings
	 */
	public String[] getSingleToolFromDatabase(String name) {
		return this.getDataBaseController().getIdbController().queryByName(name);
	}
	
	/**
	 * This getSingleToolFromDatabase() method will be called in the ServerModelControllerRunner class, and it will run the queryById() method
	 * from the InventoryDBController to find a tool by ID in the database.
	 * @return the tool in an array of strings
	 */
	public String[] getSingleToolFromDatabase(int id) {
		return this.getDataBaseController().getIdbController().queryById(id);
	}
	
	/**
	 * The decreaseToolQuantity() method will be called in the ServerModelController class, and it will run the decreaseQuantity() method 
	 * from the InventoryDBController to "sell" a ool in the database.
	 * @param name is the name of the tool to be "sold"
	 */
	public void decreaseToolQuantity(String name) {
		this.getDataBaseController().getIdbController().decreaseQuantity(name);	
	}
	
	/**
	 * The orderMoreTools() method will be called in the ServerModelController class, and it will run the queryByName() method from the 
	 * InventoryDBController. It will construct a Tool object with the returned data, build an OrderLine for the Tool, check if a new Order
	 * needs to be created, check if a new OrderLine needs to be created or an existing OrderLine needs to be updated, update the Tool's quantity
	 * in the database, and send a success message to the client.
	 * @param name is the name of the tool
	 */
	public void orderMoreTools(String name) {
		String [] arr = this.getDataBaseController().getIdbController().queryByName(name);
		if (arr == null) {
			this.sendMarkerStringToClient("Please enter a valid Tool to decrease.");
			return;
		}
		this.buildTools(arr);
		this.getShop().buildOrderLine();
		this.insertOrderIntoDatabase();
		this.insertOrderLineIntoDatabase();
		this.updateToolQuantityInDatabase(name, this.getShop().getIm().getToolInventory().get(0).getQuantity());
		this.sendMarkerStringToClient("Tool quantity decreased successfully.");
	}
	
	/**
	 * The updateToolQuantityInDatabase() method will call the updateQuantity() method in the InventoryDBController.
	 * @param name is the name of the tool
	 * @param quantity is the new quantity that the tool's quantity will be set to
	 */
	public void updateToolQuantityInDatabase(String name, int quantity) {
		this.getDataBaseController().getIdbController().updateQuantity(name, quantity);
	}
	
	/**
	 * The insertOrderIntoDatabase() method will be call the queryOrderTable() method in the OrderDBController. 
	 */
	public void insertOrderIntoDatabase() {
		this.getDataBaseController().getOdbController().queryOrderTable(this.getShop().getIm().getOrder().getOrderID(), 
																		this.getShop().getIm().getOrder().getDate());
	}
	
	/**
	 * The insertOrderLineIntoDatabase() method will call the queryOrderLineTable() method in the OrderDBController.
	 */
	public void insertOrderLineIntoDatabase() {
		this.getDataBaseController().getOdbController().queryOrderLineTable(this.getShop().getIm().getOrder().getOrderID(),
				this.getShop().getIm().getOrder().getOrderLines().get(0).getToolID(),
				this.getShop().getIm().getOrder().getOrderLines().get(0).getSupplierID(),
				this.getShop().getIm().getOrder().getOrderLines().get(0).getOrderQuantity());
	}
 	
	/**
	 * This buildTools() method will call the buildTool() method in the Shop class on the data in the input String array.
	 * @param rawTool is the array of tool data to be put into the buildTool() method
	 * @throws NumberFormatException
	 */
	public void buildTools(String[] rawTool) throws NumberFormatException {
		if (rawTool == null)
			return;
		this.getShop().buildTool(Integer.parseInt(rawTool[0]), rawTool[1], Integer.parseInt(rawTool[2]), 
								Double.parseDouble(rawTool[3]), Integer.parseInt(rawTool[4]), rawTool[5], rawTool[6]);
	}
	
	/**
	 * This buildTools() method will call the buildTool() method in the Shop class on each String array in the input ArrayList.
	 * @param rawTools is the ArrayList of String arrays, each of which contains the tool data to be fed to the buildTool() method.
	 * @throws NumberFormatException
	 */
	public void buildTools(ArrayList<String[]> rawTools) throws NumberFormatException {
		if (rawTools == null)
			return;
		for (String[] i: rawTools) {
			this.getShop().buildTool(Integer.parseInt(i[0]), i[1], Integer.parseInt(i[2]), Double.parseDouble(i[3]), 
									 Integer.parseInt(i[4]), i[5], i[6]);
		}
	}
	
	/**
	 * The writeToolsToClient() method will be called in the ServerModelControllerRunner class. It will write each tool
	 * in the toolInventory ArrayList to an object to be sent to the client.
	 * @throws IOException
	 */
	public void writeToolsToClient() throws IOException {
		for (Tool tool: this.getShop().getIm().getToolInventory())
			this.getSocketOutObjects().writeObject(tool);
		this.getSocketOutObjects().writeObject(null);
	}
	
	/**
	 * The addNewCustomerToDatabase() method will be called in the ServerModelControllerRunner class. It will take an ArrayList of Strings,
	 * and call the CustomerDBController's addNewCustomer() method on the information in the ArrayList.
	 * @param rawCustomer is the ArrayList that contains the Strings of customer information
	 */
	public void addNewCustomerToDatabase(ArrayList<String> rawCustomer) {
		this.getDataBaseController().getCdbController().addNewCustomer(rawCustomer.get(1), rawCustomer.get(2), 
							  rawCustomer.get(3), rawCustomer.get(4), rawCustomer.get(5), rawCustomer.get(6));
	}
	
	/**
	 * The updateExistingCustomerInDatabase() method will be called in the ServerModelControllerRunner class. It will take an ArrayList of Strings,
	 * and call the CustomerDBController's updateCustomerInfo() method on the information in the ArrayList.
	 * @param rawCustomer is the ArrayList that contains the Strings of customer information
	 */
	public void updateExistingCustomerInDatabase(ArrayList<String> rawCustomer) {
		this.getDataBaseController().getCdbController().updateCustomerInfo(Integer.parseInt(rawCustomer.get(0)), 
			rawCustomer.get(1), rawCustomer.get(2), rawCustomer.get(3), rawCustomer.get(4), rawCustomer.get(5));
	}
	
	/**
	 * The removeExistingCustomerFromDatabase() method will be called in the ServerModelControllerRunner class. It will take an ID number,
	 * and call the CustomerDBController's removeCustomer() method on the ID number.
	 * @param id is the ID number of the customer to be removed
	 */
	public void removeExistingCustomerFromDatabase(int id) {
		this.getDataBaseController().getCdbController().removeCustomer(id);
	}
	
	/**
	 * This getSingleCustomerFromDatabase() method will be called in the ServerModelControllerRunner class. It will take an ID number, and call
	 * the CustomerDBController's queryByID() method on the ID number.
	 * @param id is the ID number of the customer to be searched for
	 * @return the String array of customer data
	 */
	public String[] getSingleCustomerFromDatabase(int id) {
		return this.getDataBaseController().getCdbController().queryById(id);
	}
	
	/**
	 * This getSingleCustomerFromDatabase() method will be called in the ServerModelControllerRunner class. It will take a name, and call
	 * the CustomerDBController's queryByID() method on the name.
	 * @param name is the name of the customer to be searched for
	 * @return the String array of customer data
	 */
	public String[] getSingleCustomerFromDatabase(String name) {
		return this.getDataBaseController().getCdbController().queryByName(name);
	}
	
	/**
	 * The getAllCustomersByTypeFromDatabase() method will be called in the ServerModelControllerRunner class. It will take a type, and call
	 * the CustomerDBController's queryCustomerTypes() method on the type.
	 * @param type is the type of customer to search for
	 * @return the ArrayList of String arrays, each of which contains customer data
	 */
	public ArrayList<String[]> getAllCustomersByTypeFromDatabase(String type) {
		return this.getDataBaseController().getCdbController().queryCustomerTypes(type);
	}
	
	/**
	 * This buildCustomers() method will be called in the ServerModelControllerRunner class. It will take an array of customer data, and call
	 * the buildCustomer() method from the Shop class on that data.
	 * @param rawCustomer is the array of customer data
	 * @throws NumberFormatException
	 */
	public void buildCustomers(String[] rawCustomer) throws NumberFormatException {
		if (rawCustomer == null)
			return;
		this.getShop().buildCustomer(Integer.parseInt(rawCustomer[0]), rawCustomer[1], rawCustomer[2], rawCustomer[3], 
													  rawCustomer[4], rawCustomer[5], rawCustomer[6]);
	}
		
	/**
	 * This buildCustomers() method will be called in the ServerModelControllerRunner class. It will take an ArrayList of String arrays, and call
	 * the buildCustomer() method from the Shop class on each String array.
	 * @param rawCustomer is the ArrayList of customer data
	 * @throws NumberFormatException
	 */
	public void buildCustomers(ArrayList<String[]> rawCustomers) throws NumberFormatException {
		if (rawCustomers == null)
			return;
		for (String[] i: rawCustomers)
			this.getShop().buildCustomer(Integer.parseInt(i[0]), i[1], i[2], i[3], i[4], i[5], i[6]);
	}
	
	/**
	 * The writeCustomersToClient() method will be called in the ServerModelControllerRunner class. It will write each customer in the customerList
	 * to an object, to be passed over to the client.
	 * @throws IOException
	 */
	public void writeCustomersToClient() throws IOException {
		for (Customer customer: this.getShop().getCm().getCustomerList())
			this.getSocketOutObjects().writeObject(customer);
		this.getSocketOutObjects().writeObject(null);
	}
	
	/**
	 * The close() method will close all the sockets.
	 */
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
