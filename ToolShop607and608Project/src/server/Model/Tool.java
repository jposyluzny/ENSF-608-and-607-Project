/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.Model;
import java.io.Serializable;

/**
 * This class will be used as a superclass for the Electrical and NonElectrical subclasses. It implements Serializable, as Tool objects will need
 * to be passed between the client and server sockets.
 */
public abstract class Tool implements Serializable {

	private static final long serialVersionUID = 1L;
    private String toolName;
    private int quantity, supplierID, toolID;
    private double price;

    /**
     * The Tool constructor takes all the following parameters to construct a Tool object. This constructor will be called in the
     * Electrical and NonElectrical subclasses.
     * @param toolName is the name of the tool
     * @param quantity is the initial quantity of the tool
     * @param price is the price of the tool
     * @param supplierID is the ID number of the tool's supplier
     * @param toolID is the ID number of the tool
     */
    public Tool (String toolName, int quantity, double price, int supplierID, int toolID) {
        this.setToolName(toolName);
        this.setQuantity(quantity);
        this.setSupplierID(supplierID);
        this.setToolID(toolID);
        this.setPrice(price);
    }

    public String getToolName() {
        return this.toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public int getQuantity() {
		return this.quantity;
	}

    public void setQuantity(int quantity) {
		this.quantity = quantity;
    }
    
    public int getSupplierID() {
		return this.supplierID;
	}

    public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
    }
    
    public int getToolID() {
		return this.toolID;
	}

    public void setToolID(int toolID) {
		this.toolID = toolID;
	}

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    /**
     * The following abstract methods will be implemented by the Electrical and NonElectrical subclasses.
     */
    public abstract String getType();

    public abstract void setType(String type);
    
    public abstract String getPowerInfo();

    public abstract void setPowerInfo(String powerInfo);

    public String toString() {
        return "".format("\nTool name: %s\nTool ID: %d\nCorresponding Supplier ID: %d\n" + 
        		"Quantity currently in inventory: %d\nTool price: %.2f",this.getToolName(),this.getToolID(),this.getSupplierID(),
        		this.getQuantity(),this.getPrice());
    }

}
