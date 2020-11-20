package server.Model;

public class Customer {
	
	private String firstName, lastName, address, postalCode, phoneNumber;
 	private int customerID;
    
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
	
    @Override
    public String toString() {
        return "".format("\nCustomer Name: %s %s\nCustomer ID: %d\nCustomer Address: %s %s\nCustomer Phone Number: %s",
        this.getFirstName(),this.getLastName(),this.getCustomerID(),this.getAddress(),this.getPostalCode(),
        this.getPhoneNumber());
    }

}