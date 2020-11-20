package server.Model;

public class International extends Supplier {
	
	private String type;
	private double importTax;
	
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
	
    @Override
    public String toString() {
        return "".format("\nSupplier name: %s\nSupplier ID: %d\nSupplier Type: %s\nSupplier contact info: %s\nSupplier address: %s"
        		+ "\nImport Tax: %f",
        this.getName(),this.getSupplierID(),this.getType(),this.getContactInfo(),this.getAddress(), this.getImportTax());
    }

}
