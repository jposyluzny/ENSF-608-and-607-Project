/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.Model;
import java.util.ArrayList;

/**
 * This class will be used to build an ArrayList of Suppliers, which will be constructed as a result of an SQL query.
 */
public class SupplierList {
	
	/**
	 * supplierList will be the ArrayList of Supplier objects
	 */
    private ArrayList <Supplier> supplierList;

    /**
     * The SupplierList constructor will construct a new empty ArrayList, which will be used to hold the Supplier objects.
     */
    public SupplierList () {
        this.setSupplierList(new ArrayList <Supplier>());
    }
    
    /**
     * The buildSupplier() method will take all the parameters necessary for creating a Supplier object, which will be either International or Local.
     * It will parse the input "type" to decide which Supplier specialization to construct, and then it will add the new object to supplierList.
     * @param name is the name of the supplier
     * @param address is the address of the supplier
     * @param contactInfo is the name of the supplier's contact representative
     * @param supplierID is the ID number of the supplier
	 * @param type is the type of the supplier, either Local or International
	 * @param importTax is the import tax rate that will be imposed on the supplier
	 */
    public void buildSupplier (String name, String address, String contactInfo, int supplierID, String type, double importTax) {
    	if (type.toLowerCase().equals("international")) 
    		this.addToolToList(new International(name, address, contactInfo, supplierID, type, importTax));
    	
    	else if (type.toLowerCase().equals("local")) 
    		this.addToolToList(new Local(name, address, contactInfo, supplierID, type));
    	
    	else 
    		System.err.println("CUSTOMER TYPE DOES NOT EXIST");
    }
  	
    /**
     * The addToolToList() method will add the constructed Supplier object to supplierList.
     * @param supplier is the Supplier object to be added
     */
  	public void addToolToList(Supplier supplier) {
  		this.supplierList.add(supplier);
  	}
  	
    /**
     * The clearList() method will clear all the entries from supplierList.
     */
	public void clearList() {
		this.getSupplierList().clear();
	}

	public ArrayList <Supplier> getSupplierList() {
		return supplierList;
	}

	public void setSupplierList(ArrayList <Supplier> supplierList) {
		this.supplierList = supplierList;
	}
  	
}
