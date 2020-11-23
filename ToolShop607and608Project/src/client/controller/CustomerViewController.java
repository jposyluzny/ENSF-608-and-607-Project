package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.DefaultListModel;

import com.sun.tools.javac.util.List;

import client.controller.InventoryViewController.ListAllTools;
import client.controller.InventoryViewController.SearchForTool;
import client.view.CustomerGUI;
import client.view.InventoryGUI;

public class CustomerViewController {
	
	private CustomerGUI customerView;
	private ClientModelController clientModelController;
	
	public CustomerViewController (ClientModelController clientModelController) {
		this.setCustomerView(new CustomerGUI());
		this.setClientModelController(clientModelController);
		customerView.addSearchListener(new SearchForCustomer());
		customerView.addClearSearchListener(new ClearParameterField());
		customerView.addSaveListener(new UpdateCustomerInfo());
		customerView.addDeleteListener(new DeleteCustomer());
		customerView.addClearClientInfoListener(new ClearCustomerInfoFields());
		customerView.addAddCustomerLitener(new AddCustomer());
		this.setJListListener();
	}
	
	class SearchForCustomer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkSearchByIDRadioButton())
				clientModelController.getClientController().getSocketOut().println("Search for Customer by ID");
			else if (checkSearchByLastNameRadioButton())
				clientModelController.getClientController().getSocketOut().println("Search for Customer by last name");
			else if (checkSearchByClientTypeRadioButton())
				clientModelController.getClientController().getSocketOut().println("Search for all Customers by type");
			
			System.out.println(customerView.getParameterField().getText());
			clientModelController.getClientController().getSocketOut().println(customerView.getParameterField().getText());
		}
		
		public boolean checkSearchByIDRadioButton() {
			return customerView.getSearchByClientId().isSelected();
		}
		
		public boolean checkSearchByLastNameRadioButton() {
			return customerView.getSearchByLastName().isSelected();
		}
		
		public boolean checkSearchByClientTypeRadioButton() {
			return customerView.getSearchByClientType().isSelected();
		}
		
	}
	
	class AddCustomer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			clientModelController.getClientController().getSocketOut().println("Add new Customer");
			ArrayList<String> arr = fetchAllCustomerInfo();
			try {
				clientModelController.getClientController().getSocketOutObjects().writeObject(arr);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class ClearParameterField implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			customerView.getParameterField().setText("");
			clearResultsPane();
		}
	}
	
	class UpdateCustomerInfo implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			clientModelController.getClientController().getSocketOut().println("Update existing Customer");
			ArrayList<String> arr = fetchAllCustomerInfo();
			try {
				clientModelController.getClientController().getSocketOutObjects().writeObject(arr);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class DeleteCustomer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			clientModelController.getClientController().getSocketOut().println("Remove customer from DB");
			ArrayList<String> arr = fetchAllCustomerInfo();
			clientModelController.getClientController().getSocketOut().println(arr.get(0));
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
		arr.add(customerView.getClientIdBox().getText());
		arr.add(customerView.getFirstNameBox().getText());
		arr.add(customerView.getLastNameBox().getText());
		arr.add(customerView.getAddressBox().getText());
		arr.add(customerView.getPostalCodeBox().getText());
		arr.add(customerView.getPhoneNumberBox().getText());
		arr.add(customerView.getTypeBox().getText());
		return arr;
	}
	
	public void clearClientInfoFields() {
		customerView.getClientIdBox().setText("");
		customerView.getFirstNameBox().setText("");
		customerView.getLastNameBox().setText("");
		customerView.getAddressBox().setText("");
		customerView.getPostalCodeBox().setText("");
		customerView.getPhoneNumberBox().setText("");
		customerView.getTypeBox().setText("");
	}
	
	public void setJListListener() {
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {
		        	String selectedItem = (String) customerView.getResultsList().getSelectedValue();
		        	String[] ss = selectedItem.split(" ");
		        	System.out.println(Arrays.toString(ss));
		        	customerView.getClientIdBox().setText(ss[0]);
		        	customerView.getFirstNameBox().setText(ss[1]);
		        	customerView.getLastNameBox().setText(ss[2]);
		        	customerView.getAddressBox().setText(ss[3] + " " + ss[4] + " " + ss[5]);
		        	customerView.getPostalCodeBox().setText(ss[6] + " " + ss[7]);
		        	customerView.getPhoneNumberBox().setText(ss[8]);
		        	customerView.getTypeBox().setText(ss[9]);
		         }
		    }
		};
		customerView.getResultsList().addMouseListener(mouseListener);
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
