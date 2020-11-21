package server.Model;

public class Shop {
	
    private Inventory im;
    private SupplierList sm;
    private CustomerList cm;

    public Shop() {
    	this.setIm(new Inventory());
    	this.setSm(new SupplierList());
    	this.setCm(new CustomerList());
    }

    public void buildTool(int toolID, String toolName, int quantity, double price, int supplierID, String type, String powerInfo) {
        this.getIm().buildTool(toolName,quantity,price,supplierID, toolID, type, powerInfo);
    }

    public void buildSupplier(String name, String address, String contactInfo, int supplierID, String type, double importTax) {
    	this.getSm().buildSupplier(name, address, contactInfo, supplierID, type, importTax);
    }
    
    public void buildCustomer(int customerID, String firstName, String lastName, String address, String postalCode, String phoneNumber, String type) {
    	this.getCm().buildCustomer(customerID, firstName, lastName, address, postalCode, phoneNumber, type);
    }
    
    public void buildOrderLine(int toolID, int supplierID, int orderQuantity) {
		this.getIm().buildOrderLine(toolID, supplierID, orderQuantity);
	}
    
    public void clearLists() {
    	this.getIm().clearList();
    	this.getSm().clearList();
    	this.getCm().clearList();
    }
    
	public Inventory getIm() {
		return im;
	}

	public void setIm(Inventory im) {
		this.im = im;
	}

	public SupplierList getSm() {
		return sm;
	}

	public void setSm(SupplierList sm) {
		this.sm = sm;
	}

	public CustomerList getCm() {
		return cm;
	}

	public void setCm(CustomerList cm) {
		this.cm = cm;
	}

}
