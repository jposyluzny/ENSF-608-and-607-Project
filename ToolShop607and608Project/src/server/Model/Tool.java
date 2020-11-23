package server.Model;

import java.io.Serializable;

//TODO: CHANGE TO ABSTRACT AND PULL UP METHODS FROM SUBCLASSES
public class Tool implements Serializable {

	private static final long serialVersionUID = 1L;
    private String toolName;
    private int quantity, supplierID, toolID;
    private double price;

    /**
     * This constructor will set all of the input tool information to the corresponding variables in this
     * object.
     * @param toolName is the name/description of the tool.
     * @param quantity is the current quantity of the tool.
     * @param price is the current set price the tool will be selling for.
     * @param supplierID is the ID number of the supplier this tool is ordered from.
     * @param toolID is the ID number of the tool for unique identification purposes.
     */
    public Tool (String toolName, int quantity, double price, int supplierID, int toolID) {
        this.setToolName(toolName);
        this.setQuantity(quantity);
        this.setSupplierID(supplierID);
        this.setToolID(toolID);
        this.setPrice(price);
    }

    //These are all getters and setters
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
     * This will override the toString method to allow each Tools unique information to be displayed.
     */
    @Override
    public String toString() {
        return "".format("\nTool name: %s\nTool ID: %d\nCorresponding Supplier ID: %d\n" + 
        "Quantity currently in inventory: %d\nTool price: %.2f",this.getToolName(),this.getToolID(),this.getSupplierID(),
        this.getQuantity(),this.getPrice());
    }

}
