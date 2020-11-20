package server.Model;

import java.util.ArrayList;

public class CustomerList {
	
    private ArrayList <Customer> customerList;

    public CustomerList () {
        this.setCustomerList(new ArrayList <Customer>());
    }

    //TODO: Fix case sensitivity when comparing strings
    public void buildCustomer(int customerID, String firstName, String lastName, String address, String postalCode, String phoneNumber, String type) {
        if (type.equals("R")) 
        	this.addCustomerToList(this.createResidential(customerID,firstName,lastName,address,postalCode,phoneNumber,type));
        else if (type.equals("C")) 
        	this.addCustomerToList(this.createCommercial(customerID,firstName,lastName,address,postalCode,phoneNumber,type));
        else 
        	System.err.println("CUSTOMER TYPE DOES NOT EXIST");
    }
    
    public Customer createResidential (int customerID, String firstName, String lastName, String address, String postalCode, String phoneNumber, String type) {
    	Customer customer = new Residential (customerID,firstName,lastName,address,postalCode,phoneNumber,type);
    	return customer;
    }
    
    public Customer createCommercial (int customerID, String firstName, String lastName, String address, String postalCode, String phoneNumber, String type) {
    	Customer customer = new Commercial (customerID,firstName,lastName,address,postalCode,phoneNumber,type);
    	return customer;
    }
    
    public void addCustomerToList(Customer customer) {
    	this.customerList.add(customer);
    }

	public ArrayList <Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList <Customer> customerList) {
		this.customerList = customerList;
	}

}
