/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.Model;

/**
 * This class represents the Local specialization of the Customer class.
 */
public class Local extends Supplier {

	private static final long serialVersionUID = 22L;
	private String type;
	
	/**
	 * The Local constructor will call the super Supplier constructor on every input parameter except for type, and then
	 * set the type to the input parameter.
     * @param name is the name of the supplier
     * @param address is the address of the supplier
     * @param contactInfo is the name of the supplier's contact representative
     * @param supplierID is the ID number of the supplier
	 * @param type is the type of the supplier, in this case will be International
	 */
	public Local (String name, String address, String contactInfo, int supplierID, String type) {
		super(name, address, contactInfo, supplierID);
		this.setType(type);
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * This method will return 0, as Local suppliers will not have import tax imposed on them.
	 */
	public double getImportTax() {
		return 0;
	}

	public void setImportTax(double importTax) {
	}
	
    public String toString() {
        return "".format("\nSupplier name: %s\nSupplier ID: %d\nSupplier Type: %s\nSupplier contact info: %s\nSupplier address: %s",
        		this.getName(),this.getSupplierID(),this.getType(),this.getContactInfo(),this.getAddress());
    }

}
