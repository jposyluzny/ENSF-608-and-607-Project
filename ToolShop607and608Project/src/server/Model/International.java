/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.Model;

/**
 * This class represents the International specialization of the Customer class.
 */
public class International extends Supplier {
	
	private static final long serialVersionUID = 21L;
	private String type;
	private double importTax;
	
	/**
	 * The International constructor will call the super Supplier constructor on every input parameter except for type and importTax, and then
	 * set the type and importTax parameters to their respective input parameters.
     * @param name is the name of the supplier
     * @param address is the address of the supplier
     * @param contactInfo is the name of the supplier's contact representative
     * @param supplierID is the ID number of the supplier
	 * @param type is the type of the supplier, in this case will be International
	 * @param importTax is the import tax rate that will be imposed on the supplier
	 */
	public International (String name, String address, String contactInfo, int supplierID, String type, double importTax) {
		super(name, address, contactInfo, supplierID);
		this.setType(type);
		this.setImportTax(importTax);
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public double getImportTax() {
		return this.importTax;
	}
	
	public void setImportTax(double importTax) {
		this.importTax = importTax;
	}
	
    public String toString() {
        return "".format("\nSupplier name: %s\nSupplier ID: %d\nSupplier Type: %s\nSupplier contact info: %s\nSupplier address: %s"
        		+ "\nImport Tax: %f",
        		this.getName(),this.getSupplierID(),this.getType(),this.getContactInfo(),this.getAddress(), this.getImportTax());
    }

}
