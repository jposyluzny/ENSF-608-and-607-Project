/*
*   File: Order.java
*   Author: Patrick Pickard
*   Date: October 6, 2020
*   ENSF 607 - Assignment 3 - Exercise 1
*/

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Class description: This class will handle the organization for ordering the new tools.
 */
public class Order {

    /**
     * This will call the createNewOrder method from the OrderLine class and pass it the required information.
     * @param toolID is the ID number of the tool for unique identification purposes.
     * @param toolName is the name/description of the tool.
     * @param quantityOrdered is the number of tools that will be ordered.
     * @param supplierName is the name of the supplier the tools need to be ordered from.
     */
	public void createNewOrder(int toolID, String toolName, int quantityOrdered, String supplierName) {
        OrderLine orderLine = new OrderLine();
        orderLine.createNewOrder(this.generate5DigitOrderID(),this.getCurrentDateTime(),toolID,toolName,quantityOrdered,supplierName);
	}

    /**
     * Will generate a random 5 digit number between 10,000 and 109,999.
     * @return is a random orderLine number
     */
    private int generate5DigitOrderID() {
        Random rand = new Random();
        return rand.nextInt(99999)+10000;
    }

    /**
     * This will get the current date.
     * @return the current date in a String format of dd/MM//yyyy
     */
    private String getCurrentDateTime() {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            return formatter.format(date);
    }
    
}
