/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.Model;
import java.io.Serializable;

/**
 * This class will be used as a superclass for the International and Local subclasses. It implements Serializable, as Supplier objects will
 * need to be passed between the client and server sockets.
 */
public abstract class Supplier implements Serializable {
	
	private static final long serialVersionUID = 2L;
    private String name, address, contactInfo;
    private int supplierID;

    /**
     * The Supplier constructor takes all the following parameters to construct a Customer object. This constructor will be called in the 
     * International and Local subclasses.
     * @param name is the name of the supplier
     * @param address is the address of the supplier
     * @param contactInfo is the name of the supplier's contact representative
     * @param supplierID is the ID number of the supplier
     */
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
    
    /**
     * The following abstract methods will be implemented by the International and Local subclasses.
     */
	public abstract String getType();
	
	public abstract void setType(String type);
	
	public abstract double getImportTax();
	
	public abstract void setImportTax(double importTax);

    public String toString() {
        return "".format("\nSupplier name: %s\nSupplier ID: %d\nSupplier contact info: %s\nSupplier address: %s",
        		this.getName(),this.getSupplierID(),this.getContactInfo(),this.getAddress());
    }

}
