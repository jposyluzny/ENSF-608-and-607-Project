/*
*   File: Shop.java
*   Author: Patrick Pickard
*   Date: October 6, 2020
*   ENSF 607 - Assignment 3 - Exercise 1
*/

/**
 * Class description: This class will serve to "direct" the requests the user makes to the correct classes that will
 * handle them. It will construct the Inventory, SupplierList and FileManager classes, and hold their instances.
 */
public class Shop {

    /**
     * These objects are given class scope so we can pass them to the functions that will require access to them.
     */
    private Inventory im;
    private SupplierList sm;
    private FileManager fm;

    /**
     * Instantiates the objects to be used in this program.
     */
    public Shop() {
        this.im = new Inventory();
        this.sm = new SupplierList();
        this.fm = new FileManager();
    }

    /**
     * Will pass the file name and inventory object to the FileManager object by calling the inputItems method.
     * @param file is the name of the .txt file holding the tool information to be stored in this program.
     */
    public void inputItems(String file) {
        fm.inputItems(file,im);
    }

    /**
     * Will pass the file name and inventory object to the FileManager object by calling the inputItems method.
     * @param file is the name of the .txt file holding the supplier information to be stored in this program.
     */
    public void inputSuppliers(String file) { 
        fm.inputSuppliers(file,sm);
    }

    /**
     * Will pass the tool name, quantity, price, supplier ID number, and tool ID number to the inventory
     * object by calling the addTool method.
       @param toolName is the name/description of the tool.
     * @param quantity is the current quantity of the tool.
     * @param price is the current set price the tool will be selling for.
     * @param supplierID is the ID number of the supplier this tool is ordered from.
     * @param toolID is the ID number of the tool for unique identification purposes.
      */
    public void addTool(String toolName, int quantity, double price, int supplierID, int toolID) {
        im.addTool(toolName,quantity,price,supplierID,toolID);
    }

    /**
     * Will pass the tool name to the inventory object by calling the findTool method.
     * @param toolName is the name/description of the tool.
     * @return is the tool object being sought.
     */
    public Tool findTool(String toolName) {
        return im.findTool(toolName);
    }

    /**
     * Will pass the tool ID number to the inventory object by calling the findTool method.
     * @param toolName is the tool ID number of the tool.
     * @return is the tool object being sought.
     */
    public Tool findTool(int toolID) {
        return im.findTool(toolID);
    }

    /**
     * Will pass the supplier ID number to the SupplierList object by calling the findSupplier method.
     * @param supplierID is the supplier ID number of the supplier.
     * @return is the supplier object being sought.
     */
    public Supplier findSupplier(int supplierID) {
        return sm.findSupplier(supplierID);
    }

    /**
     * Will pass the tool ID number to the invetory object by calling the deleteTool method.
     * @param toolID is the too ID number of the tool.
     */
    public void deleteTool(int toolID) {
        im.deleteTool(toolID);
    }

    /**
     * Will call the inventory objects printToolList() to display all tools currently held in inventory to the 
     * console.
     */
    public void printToolList() {
        im.printToolList();
    }

    /**
     * Will call the supplierList objects printSupplierList() to display all suppliers currently held in 
     * inventory to the console.
     */
    public void printSupplierList() {
        sm.printSupplierList();
    }

    /**
     * Will pass the tool ID number and the SupplierList object to the inventory object by calling the 
     * decreaseQuantity method.
     * @param toolID is the tool ID number of the tool.
     */
    public void decreaseQuantity (int toolID) {
        im.decreaseQuantity(toolID, sm);
    }

    /**
     * This will pass the Tool object to the inventory object by calling the checkOrderNeed method.
     * @param tool is the Tool object we are currently considering.
     * @return is a true or false statement depending the condition met.
     */
    public boolean checkOrderNeed(Tool tool) {
        return im.checkOrderNeed(tool);        
    }

}
