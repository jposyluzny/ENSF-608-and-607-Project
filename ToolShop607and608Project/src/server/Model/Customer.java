/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.Model;
import java.io.Serializable;

/**
 * This class will be used as a superclass for the Commercial and Residential subclasses. It implements Serializable, as Customer objects will need
 * to be passed between the client and server sockets.
 */
public abstract class Customer implements Serializable {
	
	private static final long serialVersionUID = 3L;
	private String firstName, lastName, address, postalCode, phoneNumber;
 	private int customerID;
    
	/**
	 * The Customer constructor takes all the following parameters to construct a Customer object. This constructor will be called in the
	 * Commercial and Residential subclasses.
	 * @param customerID is the ID number of the customer
	 * @param firstName is the first name of the customer
	 * @param lastName is the last name of the customer
	 * @param address is the address of the customer
	 * @param postalCode is the postal code of the customer
	 * @param phoneNumber is the phone number of the customer
	 */
    public Customer(int customerID, String firstName, String lastName, String address, String postalCode, String phoneNumber) {
    	this.setCustomerID(customerID);
    	this.setFirstName(firstName);
    	this.setLastName(lastName);
    	this.setAddress(address);
    	this.setPostalCode(postalCode);
    	this.setPhoneNumber(phoneNumber);
    }
    
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	
	/**
	 * Abstract methods getType() and setType() will be implemented by the Commercial and Residential subclasses.
	 */
	public abstract String getType();

	public abstract void setType(String type);
	
    public String toString() {
        return "".format("\nCustomer Name: %s %s\nCustomer ID: %d\nCustomer Address: %s %s\nCustomer Phone Number: %s",
        		this.getFirstName(),this.getLastName(),this.getCustomerID(),this.getAddress(),this.getPostalCode(),
        		this.getPhoneNumber());
    }

}