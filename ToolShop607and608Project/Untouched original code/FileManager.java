/*
*   File: FileManager.java
*   Author: Patrick Pickard
*   Date: October 6, 2020
*   ENSF 607 - Assignment 3 - Exercise 1
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class description: This class will handle reading the suppliers and tools from the txt files into the program.
 */
public class FileManager {

    /**
     * This will read the tools from a .txt file, line by line, and add them as tools by calling the addTool method.
     * @param file is the String name of the file the tools are to be read from
     * @param im is the Inventory object they will be stored in via ArrayList
     */
    public void inputItems(String file, Inventory im) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            line = reader.readLine();
            while (line != null) {
                String[] parts = line.split(";");
                im.addTool(parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[0]));
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This will read the suppliers from a .txt file, line by line, and add them as Suppliers by calling the addSupplier method.
     * @param file is the String name of the file the suppliers are to be read from
     * @param sm is the SupplierList object they will be stored in via ArrayList
     */
    public void inputSuppliers(String file, SupplierList sm) { 
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
            String[] parts = line.split(";");
                sm.addSupplier(parts[1], parts[2], parts[3], Integer.parseInt(parts[0]));
                line = reader.readLine();
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}