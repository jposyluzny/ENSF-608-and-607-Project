package server.Model;

public class OrderLine {
	
    private int orderID, toolID, supplierID, orderQuantity;
    
    public OrderLine(int orderID, int toolID, int supplierID, int orderQuantity) {
        this.setOrderID(orderID);
        this.setToolID(toolID);
        this.setSupplierID(supplierID);
        this.setOrderQuantity(orderQuantity);
    }

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getToolID() {
		return toolID;
	}

	public void setToolID(int toolID) {
		this.toolID = toolID;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	@Override
	public String toString() {
		return "".format("\nOrder ID: %d\tTool ID: %d\tSupplier ID: %d\tOrder Quantity: %d",this.getOrderID(),this.getToolID(),
				this.getSupplierID(),this.getOrderQuantity());
	}

}
