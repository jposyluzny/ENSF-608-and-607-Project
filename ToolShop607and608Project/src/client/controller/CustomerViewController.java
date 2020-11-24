package client.controller;

/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

import client.view.CustomerGUI;
import javax.swing.DefaultListModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class will handle sending information collected from the users inputs via the GUi to the server. It will also handle manipulating
 * the CustomerGUI with the information received from the server, and processed by the Model.
 */
public class CustomerViewController {
	
	private CustomerGUI customerView;
	private ClientModelController clientModelController;
	
	/**
	 * This will construct and set the required objects. It will also set the actionListeners required by the GUI.
	 * @param clientModelController is the instance of the clientModelController required to communicate with the server.
	 */
	public CustomerViewController (ClientModelController clientModelController) {
		this.setCustomerView(new CustomerGUI());
		this.setClientModelController(clientModelController);
		this.getCustomerView().addSearchListener(new SearchForCustomer());
		this.getCustomerView().addClearSearchListener(new ClearParameterField());
		this.getCustomerView().addSaveListener(new UpdateCustomerInfo());
		this.getCustomerView().addDeleteListener(new DeleteCustomer());
		this.getCustomerView().addClearClientInfoListener(new ClearCustomerInfoFields());
		this.getCustomerView().addAddCustomerLitener(new AddCustomer());
		this.setJListListener();
	}
	
	/**
	 * This will set the functionality required by the SearchForCustomer JButton.
	 */
	class SearchForCustomer implements ActionListener {
		@Override
		/**
		 * This will be called when the SearchForCustomer JButton is clicked.
		 */
		public void actionPerformed(ActionEvent e) {
			if (checkSearchByIDRadioButton()) {
				if (isNumeric(getCustomerView().getParameterField().getText())) {
					getClientModelController().getClientController().getSocketOut().println("Search for Customer by ID");
					sendDataToServer();
				}
			}
			else if (checkSearchByLastNameRadioButton()) {
				getClientModelController().getClientController().getSocketOut().println("Search for Customer by last name");
				sendDataToServer();
			}
			else if (checkSearchByClientTypeRadioButton()) {
				getClientModelController().getClientController().getSocketOut().println("Search for all Customers by type");
				sendDataToServer();
			}
			clearSearchParamField();
			}
		
		/**
		 * This will send the data to the server fetched from the CustomerGUI parameterField area.
		 */
		public void sendDataToServer() {
			getClientModelController().getClientController().getSocketOut().println(getCustomerView().getParameterField().getText());
		}
		
		/**
		 * Checks to see if this Radio Button is currently selected.
		 * @return true if this Radio Button is selected, and false if it is not.
		 */
		public boolean checkSearchByIDRadioButton() {
			return getCustomerView().getSearchByClientId().isSelected();
		}
		
		/**
		 * Checks to see if this Radio Button is currently selected.
		 * @return true if this Radio Button is selected, and false if it is not.
		 */
		public boolean checkSearchByLastNameRadioButton() {
			return getCustomerView().getSearchByLastName().isSelected();
		}
		
		/**
		 * Checks to see if this Radio Button is currently selected.
		 * @return true if this Radio Button is selected, and false if it is not.
		 */
		public boolean checkSearchByClientTypeRadioButton() {
			return getCustomerView().getSearchByClientType().isSelected();
		}
		
	}
	
	/**
	 * This will set the functionality required by the AddCustomer JButton.
	 */
	class AddCustomer implements ActionListener {
		@Override
		/**
		 * This will be called when the AddCustomer JButton is clicked.
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				getClientModelController().getClientController().getSocketOut().println("Add new Customer");
				getClientModelController().getClientController().getSocketOutObjects().writeObject(fetchAllCustomerInfo());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * This will set the functionality required by the ClearParameterField JButton.
	 */
	class ClearParameterField implements ActionListener {
		@Override
		/**
		 * This will be called when the ClearParameterField JButton is clicked.
		 */
		public void actionPerformed(ActionEvent e) {
			getCustomerView().getParameterField().setText("");
			clearResultsPane();
			clearSearchParamField();
		}
	}
	
	/**
	 * This will set the functionality required by the Update JButton.
	 */
	class UpdateCustomerInfo implements ActionListener {
		@Override
		/**
		 * This will be called when the Update JButton is clicked.
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				getClientModelController().getClientController().getSocketOut().println("Update existing Customer");
				getClientModelController().getClientController().getSocketOutObjects().writeObject(fetchAllCustomerInfo());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * This will set the functionality required by the Delete JButton.
	 */
	class DeleteCustomer implements ActionListener {
		@Override
		/**
		 * This will be called when the Delete JButton is clicked.
		 */
		public void actionPerformed(ActionEvent e) {
			getClientModelController().getClientController().getSocketOut().println("Remove customer from DB");
			getClientModelController().getClientController().getSocketOut().println(fetchAllCustomerInfo().get(0));
		}
	}
	
	/**
	 * This will set the functionality required by the ClearCustomerInfoFields JButton.
	 */
	class ClearCustomerInfoFields implements ActionListener {
		@Override
		/**
		 * This will be called when the ClearCustomerInfoFields JButton is clicked.
		 */
		public void actionPerformed(ActionEvent e) {
			clearClientInfoFields();
		}
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
	
	/**
	 * This will get all of the text in the Customer info fields in the Customer GUI and build it into an ArrayList.
	 * @return will return an ArrayList consisting of all of the Customer info fields.
	 */
	public ArrayList<String> fetchAllCustomerInfo () {
		ArrayList<String> arr = new ArrayList<String> ();
		arr.add(this.getCustomerView().getClientIdBox().getText());
		arr.add(this.getCustomerView().getFirstNameBox().getText());
		arr.add(this.getCustomerView().getLastNameBox().getText());
		arr.add(this.getCustomerView().getAddressBox().getText());
		arr.add(this.getCustomerView().getPostalCodeBox().getText());
		arr.add(this.getCustomerView().getPhoneNumberBox().getText());
		arr.add(this.getCustomerView().getTypeBox().getText());
		return arr;
	}
	
	/**
	 * This will clear all of the text in the Customer info fields in the Customer GUI.
	 */
	public void clearClientInfoFields() {
		this.getCustomerView().getClientIdBox().setText("");
		this.getCustomerView().getFirstNameBox().setText("");
		this.getCustomerView().getLastNameBox().setText("");
		this.getCustomerView().getAddressBox().setText("");
		this.getCustomerView().getPostalCodeBox().setText("");
		this.getCustomerView().getPhoneNumberBox().setText("");
		this.getCustomerView().getTypeBox().setText("");
	}
	
	/**
	 * This will allow the user to select the customers in the display area of the GUI and it will populate the customer info fields
	 * with this information.
	 */
	public void setJListListener() {
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {
		        	String selectedItem = (String) getCustomerView().getResultsList().getSelectedValue();
		        	String[] ss = selectedItem.split(" ");
		        	getCustomerView().getClientIdBox().setText(ss[0]);
		        	getCustomerView().getFirstNameBox().setText(ss[1]);
		        	getCustomerView().getLastNameBox().setText(ss[2]);
		        	getCustomerView().getAddressBox().setText(ss[3] + " " + ss[4] + " " + ss[5]);
		        	getCustomerView().getPostalCodeBox().setText(ss[6] + " " + ss[7]);
		        	getCustomerView().getPhoneNumberBox().setText(ss[8]);
		        	getCustomerView().getTypeBox().setText(ss[9]);
		         }
		    }
		};
		getCustomerView().getResultsList().addMouseListener(mouseListener);
	}
	
	/**
	 * This will update the GUI display area with all of the information send from the server.
	 * @param arr is an array of Strings.
	 */
	public void updateJList(ArrayList<String> arr) {
		DefaultListModel<String> model = new DefaultListModel<String> ();
		for (String i: arr)
			model.addElement(i);
		this.getCustomerView().getResultsList().setModel(model);
		this.getCustomerView().getResultsList().setSelectedIndex(0);
	}
	
	/**
	 * This will update the GUI display area with all of the information send from the server.
	 * @param s is a String.
	 */
	public void updateJList(String s) {
		DefaultListModel<String> model = new DefaultListModel<String> ();
		model.addElement(s);
		this.getCustomerView().getResultsList().setModel(model);
		this.getCustomerView().getResultsList().setSelectedIndex(0);
	}
	
	/**
	 * This will clear the main customers display area of the CustomerGUI.
	 */
	public void clearResultsPane() {
		DefaultListModel listModel = (DefaultListModel) this.getCustomerView().getResultsList().getModel();
        listModel.removeAllElements();
	}
	
	/**
	 * This will clear the searchParameterField area on the CustomerGUI.
	 */
	public void clearSearchParamField() {
		this.getCustomerView().getParameterField().setText("");
	}

	public CustomerGUI getCustomerView() {
		return customerView;
	}
	public void setCustomerView(CustomerGUI customerView) {
		this.customerView = customerView;
	}
	public ClientModelController getClientModelController() {
		return clientModelController;
	}
	public void setClientModelController(ClientModelController clientModelController) {
		this.clientModelController = clientModelController;
	}

}
