package server.Model;

public class Electrical extends Tool {
	
	private String type, powerInfo;
	
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
    
    @Override
    public String toString() {
        return "".format("\nTool name: %s\nTool ID: %d\nTool Type: %s\nCorresponding Supplier ID: %d\n" + 
        "Quantity currently in inventory: %d\nTool price: %.2f\n" + "Power Info: %s", this.getToolName(), this.getToolID(),
        this.getType(), this.getSupplierID(), this.getQuantity(), this.getPrice(), this.getPowerInfo());
    }

}
