package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		customerView.addClearSearchListener(new ClearSearchField());
		customerView.addUpdateCustomerListener(new UpdateCustomer());
		customerView.addClearCustomerFieldsListener( new ClearCustomerFields());
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
			
			clientModelController.getClientController().getSocketOut().println(customerView.getSearchParamField().getText());
		}
		
		public boolean checkSearchByIDRadioButton() {
			return customerView.getSearchByIDRaioButton().isSelected();
		}
		
		public boolean checkSearchByLastNameRadioButton() {
			return customerView.getSearchByLastNameRadioButton().isSelected();
		}
		
		public boolean checkSearchByClientTypeRadioButton() {
			return customerView.getSearchByClientTypeRadioButton().isSelected();
		}
		
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
