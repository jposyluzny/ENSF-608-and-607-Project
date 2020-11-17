/*
*   File: SupplierList.java
*   Author: Patrick Pickard
*   Date: October 6, 2020
*   ENSF 607 - Assignment 3 - Exercise 1
*/

import java.util.ArrayList;

/**
 * Class description: This class will serve to handle the "management" of all the suppliers currently held by the
 * shop owner. It will store all of the supplier objects, and handle the manipulation of these objects (searching for
 * suppliers and their accompanying information). 
 */
public class SupplierList {

    /**
     * The supplierList will store all of the Supplier objects.
     */
    private ArrayList <Supplier> supplierList;

    /**
     * Instantiates the list holding the Supplier objects.
     */
    public SupplierList () {
        this.supplierList = new ArrayList <Supplier>();
    }

    /**
     * This will add the current Supplier to the supplierList ArrayList.
     * @param name is the company name of the supplier.
     * @param address is the address of the supplier.
     * @param contactInfo is the contact information of the supplier.
     * @param supplierID is the shop owners unique ID number of the supplier for internal use.
     */
    public void addSupplier (String name, String address, String contactInfo, int supplierID) {
        Supplier supplier = new Supplier (name,address,contactInfo,supplierID);
        supplierList.add(supplier);
    }

    /**
     * This will return the Supplier object being searched for.
     * @param supplierID is the supplier ID number used to identify unique suppliers.
     * @return is a Supplier object that the user is searching for.
     */
    public Supplier findSupplier(int supplierID) {
        for (int i = 0; i < supplierList.size(); i++) {
            if (supplierList.get(i).getSupplierID() == supplierID)
                return supplierList.get(i);
        }
        //supplier is not in supplier list
        return null;
    }

    /**
     * This will print all of the Suppliers currently held in the supplierList ArrayList, from start to end.
     */
	public void printSupplierList() {
        for (int i = 0; i < supplierList.size(); i++) {
            System.out.println(supplierList.get(i).toString());
        }
	}
    
}
