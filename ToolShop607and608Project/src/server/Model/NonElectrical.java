/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.Model;

/**
 * This class represents the NonElectrical specialization of the Tool class.
 */
public class NonElectrical extends Tool {
	
	private static final long serialVersionUID = 12L;
	private String type;
	
	/**
	 * The NonElectrical constructor will call the super Tool constructor on every input parameter except for type, and then set the
	 * type to the input parameter.
	 * @param toolName is the name of the tool
	 * @param quantity is the initial quantity of the tool
	 * @param price is the price of the tool
	 * @param supplierID is the ID number of the tool's supplier
	 * @param toolID is the ID number of the tool
	 * @param type is the type of the tool, in this case type will be NonElectrical.
	 */
	public NonElectrical(String toolName, int quantity, double price, int supplierID, int toolID, String type) {
		super(toolName, quantity, price, supplierID, toolID);
		this.setType(type);
	}
	
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * This method will return null, as NonElectrical tools won't have power information.
     */
	public String getPowerInfo() {
		return null;
	}

	public void setPowerInfo(String powerInfo) {
	}
    
    public String toString() {
        return "".format("\nTool name: %s\nTool ID: %d\nTool Type: %s\nCorresponding Supplier ID: %d\n" + 
        		"Quantity currently in inventory: %d\nTool price: %.2f",this.getToolName(),this.getToolID(),this.getType(),
        		this.getSupplierID(),this.getQuantity(),this.getPrice());
    }

}
