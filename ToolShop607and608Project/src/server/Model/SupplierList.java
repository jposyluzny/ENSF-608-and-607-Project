package server.Model;

import java.util.ArrayList;

public class SupplierList {
	
    private ArrayList <Supplier> supplierList;

    public SupplierList () {
        this.setSupplierList(new ArrayList <Supplier>());
    }
    
  	//TODO: fix case sensitivity when comparing strings
  	public void buildSupplier (String name, String address, String contactInfo, int supplierID, String type, double importTax) {
  		if (type.equals("International")) 
          	this.addToolToList(new International(name, address, contactInfo, supplierID, type, importTax));
          else if (type.equals("Local")) 
          	this.addToolToList(new Local(name, address, contactInfo, supplierID, type));
          else 
          	System.err.println("CUSTOMER TYPE DOES NOT EXIST");
      }
  	
  	private void addToolToList(Supplier supplier) {
  		this.supplierList.add(supplier);
  	}
  	
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
