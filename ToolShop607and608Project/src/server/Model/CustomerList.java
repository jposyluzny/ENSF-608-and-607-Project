/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.Model;
import java.util.ArrayList;

/**
 * This class will be used to build an ArrayList of Customers, which will be constructed as a result of an SQL query.
 */
public class CustomerList {
	
	/**
	 * customerList will be the ArrayList of Customer objects
	 */
    private ArrayList <Customer> customerList;

    /**
     * The CustomerList constructor will construct a new empty ArrayList, which will be used to hold the Customer objects.
     */
    public CustomerList () {
        this.setCustomerList(new ArrayList <Customer>());
    }

    /**
     * The buildCustomer() method will take all the parameters necessary for creating a Customer object, which will be either Commercial or Residential.
     * It will parse the input "type" to decide which Customer specialization to construct, and then it will add the new object to customerList.
     * @param customerID is the ID number of the customer
     * @param firstName is the first name of the customer
     * @param lastName is the last name of the customer
     * @param address is the address of the customer
     * @param postalCode is the postal code of the customer
     * @param phoneNumber is the phone number of the customer
     * @param type is the type of the customer, which must be C or R
     */
    public void buildCustomer(int customerID, String firstName, String lastName, String address, String postalCode, String phoneNumber, String type) {
        if (type.toLowerCase().equals("r")) 
        	this.addCustomerToList(new Residential(customerID,firstName,lastName,address,postalCode,phoneNumber,type));
        
        else if (type.toLowerCase().equals("c")) 
        	this.addCustomerToList(new Commercial(customerID,firstName,lastName,address,postalCode,phoneNumber,type));
        
        else 
        	System.err.println("CUSTOMER TYPE DOES NOT EXIST");
    }
    
    /**
     * The addCustomerToList() method will add the constructed Customer object to customerList.
     * @param customer is the Customer object to be added
     */
    public void addCustomerToList(Customer customer) {
    	this.customerList.add(customer);
    }
    
    /**
     * The clearList() method will clear all the entries from customerList.
     */
	public void clearList() {
		this.getCustomerList().clear();
	}

	public ArrayList <Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList <Customer> customerList) {
		this.customerList = customerList;
	}

}
