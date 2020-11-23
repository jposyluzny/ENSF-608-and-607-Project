package server.Model;

import java.util.ArrayList;

public class Inventory {
	
    private ArrayList <Tool> toolInventory;
    private Order order;

    public Inventory() {
        this.setToolInventory(new ArrayList<Tool>());
        this.newOrder();
    }

	public void buildTool(String toolName, int quantity, double price, int supplierID, int toolID, String type, String powerInfo) {
		if (type.toLowerCase().equals("electrical")) 
        	this.addToolToList(new Electrical(toolName, quantity, price, supplierID, toolID, type, powerInfo));
        else if (type.toLowerCase().equals("non-electrical")) 
        	this.addToolToList(new NonElectrical(toolName, quantity, price, supplierID, toolID, type));
        else 
        	System.err.println("CUSTOMER TYPE DOES NOT EXIST");
    }
	
	public void newOrder() {
		if (this.getOrder() == null)
			this.setOrder(new Order());
		else if (!this.getOrder().checkToCreateNewOrder())
			this.setOrder(new Order());
	}
	
	public void clearList() {
		this.getToolInventory().clear();
		this.getOrder().clearList();
	}
	
	public void buildOrderLine() {
		if (needToOrderMoreTools()) {
			this.newOrder();
			this.getOrder().createNewOrderLine(this.getToolInventory().get(0).getToolID(), 
											   this.getToolInventory().get(0).getSupplierID(), this.numberOfToolsToOrder());
			this.getToolInventory().get(0).setQuantity(50);
		}
	}
	
	public int numberOfToolsToOrder() {
		return 50 - this.getToolInventory().get(0).getQuantity();
	}
	
	//returns true if we need to order more tools, false if we do not need to order more tools. Threshold is less than 40 tools.
	public boolean needToOrderMoreTools() {
		return this.getToolInventory().get(0).getQuantity() < 40;
	}
    
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
