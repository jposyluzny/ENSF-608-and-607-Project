package server.Model;

public class Local extends Supplier {
	
	private String type;
	
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
	
    @Override
    public String toString() {
        return "".format("\nSupplier name: %s\nSupplier ID: %d\nSupplier Type: %s\nSupplier contact info: %s\nSupplier address: %s",
        this.getName(),this.getSupplierID(),this.getType(),this.getContactInfo(),this.getAddress());
    }

}
