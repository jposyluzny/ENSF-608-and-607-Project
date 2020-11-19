/*
*   File: OrderLine.java
*   Author: Patrick Pickard
*   Date: October 6, 2020
*   ENSF 607 - Assignment 3 - Exercise 1
*/

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Class description: This class will physically build the orderLine text required in the order slip to order the
 * tools. It will also write this to a .txt file.
 */
public class OrderLine {

    /**
     * This orderLine StringBuffer will serve to build the physical text required in the order slip for ordering
     * new tools.
     */
    private StringBuffer orderLine;

    /**
     * This will instantiate a new StringBuffer orderLine.
     */
    public OrderLine() {
        orderLine = new StringBuffer();
    }

    /**
     * This will create the StringBuffer holding all the pertinent information required in the order line .txt file. It
     * will then call the writeToTxt method to write the information to a .txt file.
     * @param orderNum5Digit is the randomized 5 digit order number.
     * @param dateTime is the current date the order was created on.
     * @param toolID is the ID number of the tool for unique identification purposes.
     * @param toolName is the name/description of the tool.
     * @param quantityOrdered is the quantity of the tool to be ordered. The default order amount will always bring
     * the current number of tools up to 50.
     * @param supplierName is the name of the Supplier.
     */
    public void createNewOrder(int orderNum5Digit, String dateTime, int toolID, String toolName, int quantityOrdered, String supplierName) {
        orderLine.append("*******************************************************************************");
        String formatStr = "\nORDER ID:\t\t%d\nDate Ordered:\t\t%s\n\nItem description:\t%s\nAmount Ordered:\t\t%d\nSupplier:\t\t%s\n";
        orderLine.append(formatStr.format(formatStr, orderNum5Digit, dateTime, toolName, quantityOrdered, supplierName));

        try {
            this.writeToTxt();
        } catch (IOException e) {
            System.out.println("\n\nSomething went wrong when writing the new tool order to the .txt file...\n\n");
        }
    }
    
    /**
     * This method will physically write the created orderLine string to a .txt file named orders.txt.
     * @throws IOException will be thrown if the orders.txt file has a location/path or integrity issue.
     */
    public void writeToTxt() throws IOException {
        new File("orders.txt");
        FileWriter fw = new FileWriter("orders.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        String temp = orderLine.toString();
        pw.write(temp);
        pw.close();
        bw.close();
        fw.close();
    }
    
}
