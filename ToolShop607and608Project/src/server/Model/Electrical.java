/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.Model;

/**
 * This class represents the Electrical specialization of the Tool class.
 */
public class Electrical extends Tool {
	
	private static final long serialVersionUID = 11L;
	private String type, powerInfo;
	
	/**
	 * The Electrical constructor will call the super Tool constructor on every input parameter except for type and powerInfo, and then
	 * set the type and powerInfo parameters to their respective input parameters.
	 * @param toolName is the name of the tool
	 * @param quantity is the initial quantity of the tool
	 * @param price is the price of the tool
	 * @param supplierID is the ID number of the tool's supplier
	 * @param toolID is the ID number of the tool
	 * @param type is the type of the tool, in this case type will be Electrical
	 * @param powerInfo is the power type of the tool, which will be either AC or DC
	 */
	public Electrical(String toolName, int quantity, double price, int supplierID, int toolID, String type, String powerInfo) {
		super(toolName, quantity, price, supplierID, toolID);
		this.setType(type);
		this.setPowerInfo(powerInfo);
	}
	
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getPowerInfo() {
        return this.powerInfo;
    }

    public void setPowerInfo(String powerInfo) {
        this.powerInfo = powerInfo;
    }
    
    public String toString() {
        return "".format("\nTool name: %s\nTool ID: %d\nTool Type: %s\nCorresponding Supplier ID: %d\n" + 
        		"Quantity currently in inventory: %d\nTool price: %.2f\n" + "Power Info: %s", this.getToolName(), this.getToolID(),
        		this.getType(), this.getSupplierID(), this.getQuantity(), this.getPrice(), this.getPowerInfo());
    }

}
