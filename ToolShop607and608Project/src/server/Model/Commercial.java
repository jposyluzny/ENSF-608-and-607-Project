/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.Model;

/**
 * This class represents the Commercial specialization of the Customer class.
 */
public class Commercial extends Customer {
	
	private static final long serialVersionUID = 31L;
	private String type;

	/**
	 * The Commercial constructor will call the super Customer constructor on every input parameter except for type, and then set the 
	 * type to the input parameter.
	 * @param customerID is the ID number of the customer
	 * @param firstName is the first name of the customer
	 * @param lastName is the last name of the customer
	 * @param address is the address of the customer
	 * @param postalCode is the postal code of the customer
	 * @param phoneNumber is the phone number of the customer
	 * @param type is the type of customer, in this case type will be C
	 */
	public Commercial(int customerID, String firstName, String lastName, String address, String postalCode, String phoneNumber, String type) {
		super(customerID, firstName, lastName, address, postalCode, phoneNumber);
		this.setType(type);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
    public String toString() {
        return "".format("\nCustomer Name: %s %s\nCustomer ID: %d\nCustomer Type: %s\nCustomer Address: %s %s\nCustomer Phone Number: %s\n",
        		this.getFirstName(),this.getLastName(),this.getCustomerID(),this.getType(),this.getAddress(),this.getPostalCode(),
        		this.getPhoneNumber());
    }

}
