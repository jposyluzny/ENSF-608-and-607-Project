/*
*   File: Inventory.java
*   Author: Patrick Pickard
*   Date: October 6, 2020
*   ENSF 607 - Assignment 3 - Exercise 1
*/

import java.util.ArrayList;

/**
 * Class description: This class will serve to handle the "management" of all the tools currently held by the
 * shop owner. It will store all of the tool objects, and handle the manipulation of these objects (searching for
 * tools and their accompanying information, looking up the quantity of the tools, deleteing tools from the database,
 * updating quantity of the tools). 
 */
public class Inventory {

    /**
     * The toolInventory will store all of the Tool objects.
     * The order object will create the new order for new parts that fall under a quanitity specified.
     */
    private ArrayList <Tool> toolInventory;
    private Order order;

    /**
     * Instantiates the list holding the Tool objects.
     */
    public Inventory() {
        this.toolInventory = new ArrayList<Tool>();
        this.order = new Order();
    }

    /**
     * This will add the current Tool to the toolInventory ArrayList.
     * @param toolName is the name/description of the tool.
     * @param quantity is the current quantity of the tool.
     * @param price is the current set price the tool will be selling for.
     * @param supplierID is the ID number of the supplier this tool is ordered from.
     * @param toolID is the ID number of the tool for unique identification purposes.
     */
	public void addTool(String toolName, int quantity, double price, int supplierID, int toolID) {
        Tool tool = new Tool(toolName,quantity,price,supplierID,toolID);
        toolInventory.add(tool);
    }
    
    /**
     * This will find the tool object based on the toolID, and get its current quantity value.
     * @param toolID is the tool ID number of the tool being searched for.
     * @return is an integer value of the current quantity.
     */
    public int getQuantity(int toolID) {
        Tool current = this.findTool(toolID);
        return current.getQuantity();
    }

    /**
     * This will find the tool object based on the toolName, and get its current quantity value.
     * @param toolID is the tool name of the tool being searched for.
     * @return is an integer value of the current quantity.
     */
    public int getQuantity(String toolName) {
        Tool current = this.findTool(toolName);
        return current.getQuantity();
    }

    /**
     * Will decrease the quantity of the tool by 1 unit and check to see if additional tools need to be ordered by
     * calling the checkOrderNeed() method. If true is returned, the createNewOrder method is called to create
     * a new order.
     * @param toolID is the tool ID number of the tool to have its quantity decreased.
     */
	public void decreaseQuantity(int toolID, SupplierList sm) {
        Tool current = this.findTool(toolID);
        current.setQuantity(current.getQuantity() - 1);

        if (checkOrderNeed(current) == true) {
            order.createNewOrder(current.getToolID(), current.getToolName(), (50 - current.getQuantity()), sm.findSupplier(current.getSupplierID()).getName());

            current.setQuantity(current.getQuantity() + (50 - current.getQuantity()));
            System.out.println("".format("Additional %s's have been ordered. An order line has been generated.",current.getToolName()));
        }
    }

    /**
     * Will check to see if the current tool being sold is now under 40 tools.
     * @param quantity is the current number of tools held in inventory.
     * @return true if the quantity is less than 40 (more tools need to be ordered), and false if it is greater
     * than or equal to 40 (no tools need to be ordered)
     */
    public boolean checkOrderNeed(Tool tool) {
        if (tool.getQuantity() < 40)
            return true;
        else
            return false;
    }

    /**
     * This will return the Tool object being searched for.
     * @param toolName is the name/description of the tool to be found.
     * @return is a Tool object that the user is searching for.
     */
    public Tool findTool(String toolName) {
        for (int i = 0; i < toolInventory.size(); i++) {
            if (toolInventory.get(i).getToolName().equals(toolName))
                return toolInventory.get(i);
        }
        // tool is not in the inventory manager
        return null;
    }
    
     /**
     * This will return the Tool object being searched for.
     * @param toolID is the tool ID number of the tool to be found.
     * @return is a Tool object that the user is searching for.
     */
    public Tool findTool(int toolID) {
        for (int i = 0; i < toolInventory.size(); i++) {
            if (toolInventory.get(i).getToolID() == toolID)
                return toolInventory.get(i);
        }
        // tool is not in the inventory manager
        return null;
    }

    /**
     * This will remove a specified tool from the toolInventory ArrayList.
     * @param toolID is the tool ID number of the tool to be removed.
     */
    public void deleteTool(int toolID) {
        for (int i = 0; i < toolInventory.size(); i++) {
            if (toolInventory.get(i).getToolID() == toolID)
                toolInventory.remove(i);
        }
    }

    /**
     * This will print all of the Tools currently held in the toolInventory ArrayList, from start to end.
     */
    public void printToolList() {
        for (int i = 0; i < toolInventory.size(); i++) {
            System.out.println(toolInventory.get(i).toString());
        }
    }
    
}
