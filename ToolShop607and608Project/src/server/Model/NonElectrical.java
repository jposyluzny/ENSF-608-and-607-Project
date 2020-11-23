package server.Model;

public class NonElectrical extends Tool {
	
	private static final long serialVersionUID = 12L;
	private String type;
	
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
