package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import client.view.InventoryGUI;

public class InventoryViewController {
	
	private InventoryGUI inventoryView;
	private ClientModelController clientModelController;
	
	public InventoryViewController(InventoryGUI inventoryView, ClientModelController clientModelController) {
		this.setInventoryView(inventoryView);
		this.setClientModelController(clientModelController);
		inventoryView.addSearchForAllToolsListener(new ListAllTools());
		inventoryView.addSearchListener(new SearchForTool());
	}
	
	class ListAllTools implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			clientModelController.getClientController().getSocketOut().println("List all Tools");
		}
	}
	
	class SearchForTool implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkSearchByNameRadioButton())
				clientModelController.getClientController().getSocketOut().println("Search Tool by Name");
			else if (checksearchByIDRadioButton())
				clientModelController.getClientController().getSocketOut().println("Search Tool by ID");
			else if (checkQuantityRadioButton())
				clientModelController.getClientController().getSocketOut().println("Check Quantity");
			else if (checkDecreaseQuantityRadioButton())
				clientModelController.getClientController().getSocketOut().println("Decrease Quantity");
			
			clientModelController.getClientController().getSocketOut().println(inventoryView.getSearchParamField().getText());
		}
		
		public boolean checkSearchByNameRadioButton() {
			if (inventoryView.getSearchByNameRadioButton().isSelected())
				return true;
			else
				return false;
		}
		
		public boolean checksearchByIDRadioButton() {
			if (inventoryView.getSearchByIDRadioButton().isSelected())
				return true;
			else
				return false;
		}
		
		public boolean checkQuantityRadioButton() {
			if (inventoryView.getCheckQuantityRadioButton().isSelected())
				return true;
			else
				return false;
		}
		
		public boolean checkDecreaseQuantityRadioButton() {
			if (inventoryView.getDecreaseQuantityRadioButton().isSelected())
				return true;
			else
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
