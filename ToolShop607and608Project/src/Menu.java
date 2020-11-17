/*
*   File: Menu.java
*   Author: Patrick Pickard
*   Date: October 6, 2020
*   ENSF 607 - Assignment 3 - Exercise 1
*/

import java.util.Scanner;

/**
 * Class description: THis will serve as the front end of the application, handling the users inputs and interactions
 * with the program, and outputting the required information.
 */
public class Menu {
    public static void main(String[] args) throws Exception {
        Shop s = new Shop();
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        s.inputItems("items.txt");
        s.inputSuppliers("suppliers.txt");

        while (flag) {
            System.out.println("********************************************************************");
            System.out.println("Tool Shop Inventory Program Menu.\n");
            System.out.print("1.\tPrint list of tools currently in inventory\n" +
            "2.\tSearch for tool by tool name\n" +
            "3.\tSearch for tool by tool ID\n" +
            "4.\tCheck item quantity by tool name\n" + 
            "5.\tCheck item quantity by tool ID\n" +
            "6.\tDecrease item quantity (by 1 unit) by tool ID\n" +
            "7.\tDelete tool by tool ID\n" +
            "8.\tPrint list of Suppliers\n" +
            "9.\tSearch for Supplier by Supplier ID\n" +
            "10.\tQuit Inventory Program\n");

            try {
            System.out.println("\nPlease enter a number in the above list you wish to execute."); 
            int userInput = sc.nextInt();
            sc.nextLine();

            switch (userInput) {
                case 1: //print list of all tools in inventory system
                    s.printToolList();
                    System.out.println("");
                    break;
                case 2: //search tool by tool name
                    try {
                        System.out.println("Please enter the tool name: ");
                        String temp = sc.nextLine();
                        if (s.findTool(temp) == null) {
                            System.out.println("PLEASE ENTER A VALID TOOL NAME.");
                            break;
                        }
                        System.out.println(s.findTool(temp));
                        System.out.println("");
                    } catch (NullPointerException ne) {
                        System.out.println("\n\nPLEASE ENTER A VALID TOOL NAME.\n\n");
                    } catch (Exception e) {
                        System.out.println("\n\nSomething went wrong...\n\n");
                    }
                    break;
                case 3: //search tool by tool ID
                    try {
                        System.out.println("Please enter the tool ID: ");
                        int temp = sc.nextInt();
                        sc.nextLine();
                        if (s.findTool(temp) == null) {
                            System.out.println("PLEASE ENTER A VALID TOOL NAME.");
                            break;
                        }
                        System.out.println(s.findTool(temp));
                        System.out.println("");
                    } catch (NullPointerException ne) {
                        System.out.println("\n\nPLEASE ENTER A VALID TOOL ID.\n\n");
                    } catch (Exception e) {
                        System.out.println("\n\nSomething went wrong...\n\n");
                        sc.nextLine();
                    }
                    break;
                case 4: //check item quantity by tool name
                    try {
                        System.out.println("Please enter the tool name: ");
                        String temp = sc.nextLine();
                        if (s.findTool(temp) == null) {
                            System.out.println("PLEASE ENTER A VALID TOOL NAME.");
                            break;
                        }
                        System.out.println("Current Quantity in stock: " + s.findTool(temp).getQuantity());
                        System.out.println("");
                    } catch (NullPointerException ne) {
                        System.out.println("\n\nPLEASE ENTER A VALID TOOL NAME.\n\n");
                    } catch (Exception e) {
                        System.out.println("\n\nSomething went wrong...\n\n");
                    }
                    break;
                case 5: //check item quantity by tool ID
                    try {
                        System.out.println("Please enter the tool ID: ");
                        int temp = sc.nextInt();
                        sc.nextLine();
                        if (s.findTool(temp) == null) {
                            System.out.println("PLEASE ENTER A VALID TOOL ID.");
                            break;
                        }
                        System.out.println("Current Quantity in stock: " + s.findTool(temp).getQuantity());
                        System.out.println("");
                    } catch (NullPointerException ne) {
                        System.out.println("\n\nPLEASE ENTER A VALID TOOL ID.\n\n");
                    } catch (Exception e) {
                        System.out.println("\n\nSomething went wrong...\n\n");
                        sc.nextLine();
                    }
                    break;
                case 6: //update item quantity by tool ID
                    try {
                        System.out.println("Please enter the tool ID: ");
                        int toolID = sc.nextInt();
                        sc.nextLine();
                        if (s.findTool(toolID) == null) {
                            System.out.println("PLEASE ENTER A VALID TOOL ID.");
                            break;
                        }                  
                        s.decreaseQuantity(toolID);
                        System.out.println("Quantity has been updated to: " + s.findTool(toolID).getQuantity());
                        System.out.println("");
                    } catch (NullPointerException ne) {
                        System.out.println("\n\nPLEASE ENTER A VALID TOOL ID.\n\n");
                    } catch (Exception e) {
                        System.out.println("\n\nSomething went wrong...\n\n");
                        sc.nextLine();
                    }
                    break;
                case 7: //delete a tool from inventory
                    try {
                        System.out.println("Please enter the tool ID: ");
                        int temp = sc.nextInt();
                        sc.nextLine();
                        if (s.findTool(temp) == null) {
                            System.out.println("PLEASE ENTER A VALID TOOL ID.");
                            break;
                        }
                        s.deleteTool(temp);
                        System.out.println("Tool has been deleted from the inventory manager.");
                        System.out.println("");
                    } catch (NullPointerException ne) {
                        System.out.println("\n\nPLEASE ENTER A VALID TOOL ID.\n\n");
                    } catch (Exception e) {
                        System.out.println("\n\nSomething went wrong...\n\n");
                        sc.nextLine();
                    }
                    break;
                case 8: //print list of all suppliers in the supplier system
                    s.printSupplierList();
                    System.out.println("");
                    break;
                case 9: //search for supplier by supplier ID
                    try {
                        System.out.println("Please enter the Supplier ID: ");
                        int temp = sc.nextInt();
                        sc.nextLine();
                        if (s.findSupplier(temp) == null) {
                            System.out.println("PLEASE ENTER A VALID SUPPLIER ID.");
                            break;
                        }
                        System.out.println(s.findSupplier(temp));
                        System.out.println("");
                    } catch (NullPointerException ne) {
                        System.out.println("\n\nPLEASE ENTER A VALID SUPPLIER ID.\n\n");
                    } catch (Exception e) {
                        System.out.println("\n\nSomething went wrong...\n\n");
                        sc.nextLine();
                    }
                    break;
                case 10:
                    flag = false;
                    break;
            }
        } catch (Exception e) {
            System.out.println("\n\nSomething went wrong...\n\n");
            sc.nextLine();
        }
        }
        sc.close();
        System.out.println("Program closed.");
    }

}
