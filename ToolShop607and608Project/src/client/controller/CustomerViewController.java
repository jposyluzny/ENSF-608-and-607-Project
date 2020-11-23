package client.controller;

import client.view.CustomerGUI;
import javax.swing.DefaultListModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
<<<<<<< HEAD
=======
import java.util.Arrays;

import javax.swing.DefaultListModel;
import com.sun.tools.javac.util.List;

import client.controller.InventoryViewController.ListAllTools;
import client.controller.InventoryViewController.SearchForTool;
import client.view.CustomerGUI;
import client.view.InventoryGUI;
>>>>>>> 2a536b4a5cb5f39ef4ae78f799df13beb3708ab2

public class CustomerViewController {
	
	private CustomerGUI customerView;
	private ClientModelController clientModelController;
	
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
	
	class SearchForCustomer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkSearchByIDRadioButton())
				getClientModelController().getClientController().getSocketOut().println("Search for Customer by ID");
			else if (checkSearchByLastNameRadioButton())
				getClientModelController().getClientController().getSocketOut().println("Search for Customer by last name");
			else if (checkSearchByClientTypeRadioButton())
				getClientModelController().getClientController().getSocketOut().println("Search for all Customers by type");
			
			getClientModelController().getClientController().getSocketOut().println(getCustomerView().getParameterField().getText());
		}
		
		public boolean checkSearchByIDRadioButton() {
			return getCustomerView().getSearchByClientId().isSelected();
		}
		
		public boolean checkSearchByLastNameRadioButton() {
			return getCustomerView().getSearchByLastName().isSelected();
		}
		
		public boolean checkSearchByClientTypeRadioButton() {
			return getCustomerView().getSearchByClientType().isSelected();
		}
		
	}
	
	class AddCustomer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				getClientModelController().getClientController().getSocketOut().println("Add new Customer");
				getClientModelController().getClientController().getSocketOutObjects().writeObject(fetchAllCustomerInfo());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	class ClearParameterField implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getCustomerView().getParameterField().setText("");
			clearResultsPane();
		}
	}
	
	class UpdateCustomerInfo implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				getClientModelController().getClientController().getSocketOut().println("Update existing Customer");
				getClientModelController().getClientController().getSocketOutObjects().writeObject(fetchAllCustomerInfo());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	class DeleteCustomer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getClientModelController().getClientController().getSocketOut().println("Remove customer from DB");
			getClientModelController().getClientController().getSocketOut().println(fetchAllCustomerInfo().get(0));
		}
	}
	
	class ClearCustomerInfoFields implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			clearClientInfoFields();
		}
	}
	
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
	
	public void clearClientInfoFields() {
		this.getCustomerView().getClientIdBox().setText("");
		this.getCustomerView().getFirstNameBox().setText("");
		this.getCustomerView().getLastNameBox().setText("");
		this.getCustomerView().getAddressBox().setText("");
		this.getCustomerView().getPostalCodeBox().setText("");
		this.getCustomerView().getPhoneNumberBox().setText("");
		this.getCustomerView().getTypeBox().setText("");
	}
	
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
	
	public void updateJList(ArrayList<String> arr) {
		DefaultListModel<String> model = new DefaultListModel<String> ();
		for (String i: arr)
			model.addElement(i);
		this.getCustomerView().getResultsList().setModel(model);
		this.getCustomerView().getResultsList().setSelectedIndex(0);
	}
	
	public void updateJList(String s) {
		DefaultListModel<String> model = new DefaultListModel<String> ();
		model.addElement(s);
		this.getCustomerView().getResultsList().setModel(model);
		this.getCustomerView().getResultsList().setSelectedIndex(0);
	}
	
	public void clearResultsPane() {
		DefaultListModel listModel = (DefaultListModel) this.getCustomerView().getResultsList().getModel();
        listModel.removeAllElements();
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
