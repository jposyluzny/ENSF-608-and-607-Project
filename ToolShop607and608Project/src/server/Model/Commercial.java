package server.Model;

public class Commercial extends Customer {
	
	private String type;

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
	
    @Override
    public String toString() {
        return "".format("\nCustomer Name: %s %s\nCustomer ID: %d\nCustomer Type: %s\nCustomer Address: %s %s\nCustomer Phone Number: %s\n",
        this.getFirstName(),this.getLastName(),this.getCustomerID(),this.getType(),this.getAddress(),this.getPostalCode(),
        this.getPhoneNumber());
    }

}
