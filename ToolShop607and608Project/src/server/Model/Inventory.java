/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.Model;
import java.util.ArrayList;

/**
 * This class is responsible for building a list of Tool objects, determining whether or not an order or orderline would need to be generated, and then 
 * building both or either of those if needed.
 */
public class Inventory {
	
	/**
	 * toolInventory will be the ArrayList of Tool objects
	 */
    private ArrayList <Tool> toolInventory;
    private Order order;

    /**
     * The Inventory constructor will assign the new Inventory object an empty ArrayList which will take tools, and will also assign a new order if one 
     * doesn't already exist.
     */
    public Inventory() {
        this.setToolInventory(new ArrayList<Tool>());
        this.newOrder();
    }

    /**
     * The buildTool() method will take all the parameters necessary for creating a Tool object, which will be either Electrical or NonElectrical.
     * It will parse the input "type" to decide which Tool specialization to construct, and then it will add the new object to toolInventory.
     * @param toolName is the name of the tool
     * @param quantity is the initial quantity of the tool
     * @param price is the price of the tool
     * @param supplierID is the ID number of the tool's supplier
     * @param toolID is the ID number of the tool
     * @param type is the type of tool, either Electrical or NonElectrical
     * @param powerInfo is the power information of the tool, either AC or DC
     */
	public void buildTool(String toolName, int quantity, double price, int supplierID, int toolID, String type, String powerInfo) {
		if (type.toLowerCase().equals("electrical")) 
        	this.addToolToList(new Electrical(toolName, quantity, price, supplierID, toolID, type, powerInfo));
        else if (type.toLowerCase().equals("non-electrical")) 
        	this.addToolToList(new NonElectrical(toolName, quantity, price, supplierID, toolID, type));
        else 
        	System.err.println("CUSTOMER TYPE DOES NOT EXIST");
    }
	
	/**
	 * The newOrder() method will check whether or not an Order object already exists for this Inventory object. If it doesn't, then a new Order will be
	 * created and assigned to the Inventory object. If one already exists, then it will be checked to see whether or not it is an Order for the current
	 * day or not. If it is not the current day's Order, a new one will be created for the current day.
	 */
	public void newOrder() {
		if (this.getOrder() == null)
			this.setOrder(new Order());
		else if (!this.getOrder().checkToCreateNewOrder())
			this.setOrder(new Order());
	}
	
	/**
	 * The clearList() method will empty the toolInventory and order lists.
	 */
	public void clearList() {
		this.getToolInventory().clear();
		this.getOrder().clearList();
	}
	
	/**
	 * The buildOrderLine() method will check whether or not a Tool needs to be ordered. If it does, a new Order will be created if needed, and then
	 * an OrderLine for the Tool will be created and added to the Order.
	 */
	public void buildOrderLine() {
		if (needToOrderMoreTools()) {
			this.newOrder();
			this.getOrder().createNewOrderLine(this.getToolInventory().get(0).getToolID(), 
											   this.getToolInventory().get(0).getSupplierID(), this.numberOfToolsToOrder());
			this.getToolInventory().get(0).setQuantity(50);
		}
	}
	
	/**
	 * The numberOfToolsToOrder() will calculate the amount of the Tool that needs to be ordered.
	 */
	public int numberOfToolsToOrder() {
		return 50 - this.getToolInventory().get(0).getQuantity();
	}
	
	/**
	 * The needToOrderMoreTools() method will decide whether or not a Tool needs to be ordered. If it needs to be, true will be returned. If not,
	 * false will be returned.
	 */
	public boolean needToOrderMoreTools() {
		return this.getToolInventory().get(0).getQuantity() < 40;
	}
    
	/**
	 * The addToolToList() method will add a Tool object to the toolInventory ArrayList.
	 * @param tool is the Tool to be added to the list
	 */
    public void addToolToList(Tool tool) {
    	this.getToolInventory().add(tool);
    }

	public ArrayList <Tool> getToolInventory() {
		return toolInventory;
	}

	public void setToolInventory(ArrayList <Tool> toolInventory) {
		this.toolInventory = toolInventory;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
