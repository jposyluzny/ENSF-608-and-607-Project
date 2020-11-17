/*
*   File: Supplier.java
*   Author: Patrick Pickard
*   Date: October 6, 2020
*   ENSF 607 - Assignment 3 - Exercise 1
*/

/**
 * Class description: This class will hold all of the information associated with each supplier. It will provide
 * methods to access and change this information from outside of the class as well. It will also provide a simple
 * toString override method to display all information contained in each Supplier object.
 */
public class Supplier {

    /**
     * This information will be supplier specific.
     */
    private String name, address, contactInfo;
    private int supplierID;

    /**
     * This constructor will set all of the input supplier information to the corresponding variables in this
     * object.
     * @param name is the company name of the supplier.
     * @param address is the address of the supplier.
     * @param contactInfo is the contact information of the supplier.
     * @param supplierID is the shop owners unique ID number of the supplier for internal use.
     */
    public Supplier (String name, String address, String contactInfo, int supplierID) {
        this.setName(name);
        this.setAddress(address);
        this.setContactInfo(contactInfo);
        this.setSupplierID(supplierID);
    }

    //These are all getters and setters
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactInfo() {
        return this.contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public int getSupplierID() {
        return this.supplierID;
    }

    public void setSupplierID(int supplierID) {
        this.supplierID = supplierID;
    }

    /**
     * This will override the toString method to allow each Suppliers unqiue information to be displayed.
     */
    @Override
    public String toString() {
        return "".format("\nSupplier name: %s\nSupplier ID: %d\nSupplier contact info: %s\nSupplier address: %s",
        this.getName(),this.getSupplierID(),this.getContactInfo(),this.getAddress());
    }
    
}
