package server.Model;

import java.io.Serializable;

public abstract class Supplier implements Serializable {
	
	private static final long serialVersionUID = 2L;
    private String name, address, contactInfo;
    private int supplierID;

    public Supplier (String name, String address, String contactInfo, int supplierID) {
        this.setName(name);
        this.setAddress(address);
        this.setContactInfo(contactInfo);
        this.setSupplierID(supplierID);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return this.contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getSupplierID() {
        return this.supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }
    
	public abstract String getType();
	
	public abstract void setType(String type);
	
	public abstract double getImportTax();
	
	public abstract void setImportTax(double importTax);

    public String toString() {
        return "".format("\nSupplier name: %s\nSupplier ID: %d\nSupplier contact info: %s\nSupplier address: %s",
        		this.getName(),this.getSupplierID(),this.getContactInfo(),this.getAddress());
    }

}
