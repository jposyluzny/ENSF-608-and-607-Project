/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.Model;

/**
 * This class is responsible for handling the Inventory, SupplierList, and CustomerList classes, and calling their methods.
 */
public class Shop {
	
    private Inventory im;
    private SupplierList sm;
    private CustomerList cm;

    /**
     * The Shop constructor builds Inventory, SupplierList, and CustomerList objects and assigns them to the Shop object.
     */
    public Shop() {
    	this.setIm(new Inventory());
    	this.setSm(new SupplierList());
    	this.setCm(new CustomerList());
    }

    /**
     * The buildTool() method calls the buildTool() method from the Inventory class with the input parameters.
	 * @param toolName is the name of the tool
	 * @param quantity is the initial quantity of the tool
	 * @param price is the price of the tool
	 * @param supplierID is the ID number of the tool's supplier
	 * @param toolID is the ID number of the tool
	 * @param type is the type of the tool, Electrical or NonElectrical
	 * @param powerInfo is the power type of the tool, which will be either AC or DC
	 */
    public void buildTool(int toolID, String toolName, int quantity, double price, int supplierID, String type, String powerInfo) {
        this.getIm().buildTool(toolName,quantity,price,supplierID, toolID, type, powerInfo);
    }

    /**
     * The buildSupplier() method calls the buildSupplier() method from the SupplierList class with the input parameters.
     * @param name is the name of the supplier
     * @param address is the address of the supplier
     * @param contactInfo is the name of the supplier's contact representative
     * @param supplierID is the ID number of the supplier
	 * @param type is the type of the supplier, Local or International
	 * @param importTax is the import tax rate that will be imposed on the supplier
	 */
    public void buildSupplier(String name, String address, String contactInfo, int supplierID, String type, double importTax) {
    	this.getSm().buildSupplier(name, address, contactInfo, supplierID, type, importTax);
    }
    
    /**
     * The buildCustomer() method calls the buildCustomer() method from the CustomerList class with the input parameters.
	 * @param customerID is the ID number of the customer
	 * @param firstName is the first name of the customer
	 * @param lastName is the last name of the customer
	 * @param address is the address of the customer
	 * @param postalCode is the postal code of the customer
	 * @param phoneNumber is the phone number of the customer
	 * @param type is the type of customer, Commercial or Residential
	 */
    public void buildCustomer(int customerID, String firstName, String lastName, String address, String postalCode, String phoneNumber, String type) {
    	this.getCm().buildCustomer(customerID, firstName, lastName, address, postalCode, phoneNumber, type);
    }
    
    /**
     * The buildOrderLine() method calls the buildOrderLine() method from the Inventory class.
     */
    public void buildOrderLine() {
		this.getIm().buildOrderLine();
	}
    
    /**
     * The clearLists() method will call the clearList() methods from the Inventory, SupplierList, and CustomerList classes.
     */
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
