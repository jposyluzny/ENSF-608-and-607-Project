package client.controller;

/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

/**
 * This class will handle sending information collected from the users inputs via the GUi to the server. It will also handle manipulating
 * the InventoryGUI with the information received from the server, and processed by the Model.
 */
import client.view.InventoryGUI;
import server.Model.Tool;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InventoryViewController {
	
	private InventoryGUI inventoryView;
	private ClientModelController clientModelController;
	
	/**
	 * This will construct and set the required objects. It will also set the actionListeners required by the GUI.
	 * @param clientModelController is the instance of the clientModelController required to communicate with the server.
	 */
	public InventoryViewController(ClientModelController clientModelController) {
		this.setInventoryView(new InventoryGUI());
		this.setClientModelController(clientModelController);
		this.getInventoryView().addSearchForAllToolsListener(new ListAllTools());
		this.getInventoryView().addSearchListener(new SearchForTool());
	}
	
	/**
	 * This will set the functionality required by the ListAllTools JButton.
	 */
	class ListAllTools implements ActionListener {
		@Override
		/**
		 * This will be called when the ListAllTools JButton is clicked.
		 */
		public void actionPerformed(ActionEvent e) {
			getClientModelController().getClientController().getSocketOut().println("List all Tools");
		}
	}
	
	/**
	 * This will set the functionality required by the SearchForTool JButton.
	 */
	class SearchForTool implements ActionListener {
		@Override
		/**
		 * This will be called when the SearchForTool JButton is clicked.
		 */
		public void actionPerformed(ActionEvent e) {
			if (checkSearchByNameRadioButton()) {
				getClientModelController().getClientController().getSocketOut().println("Search Tool by Name");
				sendDataToServer();
			}
			else if (checksearchByIDRadioButton()) {
				if (isNumeric(getInventoryView().getSearchParamField().getText())) {
					getClientModelController().getClientController().getSocketOut().println("Search Tool by ID");
					sendDataToServer();
				}
				else {
					clearResultsPane();
					clearSearchParamField();
					updateResultsAreaWithDecreaseQuantityNotification("Please enter a valid Tool ID.");
				}
			}
			else if (checkQuantityRadioButton()) {
				getClientModelController().getClientController().getSocketOut().println("Check Quantity");
				sendDataToServer();
			}
			else if (checkDecreaseQuantityRadioButton()) {
				getClientModelController().getClientController().getSocketOut().println("Decrease Quantity");
				sendDataToServer();
			}
		}
		
		/**
		 * This will send the data to the server fetched from the CustomerGUI parameterField area.
		 */
		public void sendDataToServer() {
			getClientModelController().getClientController().getSocketOut().println(getInventoryView().getSearchParamField().getText());
		}
		
		/**
		 * Checks to see if this Radio Button is currently selected.
		 * @return true if this Radio Button is selected, and false if it is not.
		 */
		public boolean checkSearchByNameRadioButton() {
			return getInventoryView().getSearchByNameRadioButton().isSelected();
		}
		
		/**
		 * Checks to see if this Radio Button is currently selected.
		 * @return true if this Radio Button is selected, and false if it is not.
		 */
		public boolean checksearchByIDRadioButton() {
			return getInventoryView().getSearchByIDRadioButton().isSelected();
		}
		
		/**
		 * Checks to see if this Radio Button is currently selected.
		 * @return true if this Radio Button is selected, and false if it is not.
		 */
		public boolean checkQuantityRadioButton() {
			return getInventoryView().getCheckQuantityRadioButton().isSelected();
		}
		
		/**
		 * Checks to see if this Radio Button is currently selected.
		 * @return true if this Radio Button is selected, and false if it is not.
		 */
		public boolean checkDecreaseQuantityRadioButton() {
			return getInventoryView().getDecreaseQuantityRadioButton().isSelected();
		}
	}
	
	/**
	 * This will clear the results display area on the InventoryGUI. It will then print all of the tools data fields (with the 
	 * toString() method for each object) to the results display area.
	 * @param tools is the ArrayList holding all of the Tool objects.
	 */
	public void updateResultsAreaWithTools(ArrayList<Tool> tools) {
		this.clearResultsPane();
		for (Tool t: tools)
			this.getInventoryView().getResultsArea().append(t.toString() + "\n");
		this.clearSearchParamField();
	}
	
	/**
	 * This will clear the results display area on the InventoryGUI. It will then print all of the tools quantity and tool name
	 * to the results display area.
	 * @param tools is the ArrayList holding all of the Tool objects.
	 */
	public void updateResultsAreaWithQuantity(ArrayList<Tool> tools) {
		this.clearResultsPane();
		for (Tool t: tools)
			this.getInventoryView().getResultsArea().append("".format("The remaining quantity of %s is %d\n", 
																		   t.getToolName(), t.getQuantity()));
		this.clearSearchParamField();
	}
	
	/**
	 * This will clear the results display area on the InventoryGUI. It will take a string and display it on the results display area.
	 * @param s is a string sent from the client.
	 */
	public void updateResultsAreaWithDecreaseQuantityNotification(String s) {
		this.clearResultsPane();
		this.getInventoryView().getResultsArea().append(s + "\n");
		this.clearSearchParamField();
	}
	
	/**
	 * This will clear the results display area on the InventoryGUI.
	 */
	public void clearResultsPane() {
		this.getInventoryView().getResultsArea().setText("");
	}

	/**
	 * This will clear the searchParameterField area on the InventoryGUI.
	 */
	public void clearSearchParamField() {
		this.getInventoryView().getSearchParamField().setText("");
	}
	
	/**
	 * This will handle errors stemming from if the Client does not input an integer when searching for a client by ID.
	 * @param str is the input text from the Client.
	 * @return This will return true if the String can be parsed for an integer, and false if it cannot.
	 */
	public static boolean isNumeric(String str) { 
		  try {  
		    Integer.parseInt(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
	}
	
	public InventoryGUI getInventoryView() {
		return inventoryView;
	}
	
	public void setInventoryView(InventoryGUI inventoryView) {
		this.inventoryView = inventoryView;
	}
	public ClientModelController getClientModelController() {
		return clientModelController;
	}
	public void setClientModelController(ClientModelController clientModelController) {
		this.clientModelController = clientModelController;
	}

}
