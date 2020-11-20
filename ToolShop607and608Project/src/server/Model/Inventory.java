package server.Model;

import java.util.ArrayList;

public class Inventory {
	
    private ArrayList <Tool> toolInventory;
    private Order order;

    public Inventory() {
        this.setToolInventory(new ArrayList<Tool>());
        this.newOrder();
    }

    //TODO: fix case sensitivity when comparing strings
	public void buildTool(String toolName, int quantity, double price, int supplierID, int toolID, String type, String powerInfo) {
		if (type.equals("Electrical")) 
        	this.addToolToList(new Electrical(toolName, quantity, price, supplierID, toolID, type, powerInfo));
        else if (type.equals("Non-Electrical")) 
        	this.addToolToList(new NonElectrical(toolName, quantity, price, supplierID, toolID, type));
        else 
        	System.err.println("CUSTOMER TYPE DOES NOT EXIST");
    }
	
	public void newOrder() {
		if (this.getOrder() == null)
			this.setOrder(new Order());
		else if (this.getOrder().checkToCreateNewOrder())
			this.setOrder(new Order());
	}
	
	public void clearList() {
		this.getToolInventory().clear();
	}
	
	public void buildOrderLine(int toolID, int supplierID, int orderQuantity) {
		this.newOrder();
		order.createNewOrderLine(toolID, supplierID, orderQuantity);
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
