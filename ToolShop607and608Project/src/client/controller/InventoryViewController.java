package client.controller;

import client.view.InventoryGUI;
import server.Model.Tool;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InventoryViewController {
	
	private InventoryGUI inventoryView;
	private ClientModelController clientModelController;
	
	public InventoryViewController(ClientModelController clientModelController) {
		this.setInventoryView(new InventoryGUI());
		this.setClientModelController(clientModelController);
		this.getInventoryView().addSearchForAllToolsListener(new ListAllTools());
		this.getInventoryView().addSearchListener(new SearchForTool());
	}
	
	class ListAllTools implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			getClientModelController().getClientController().getSocketOut().println("List all Tools");
		}
	}
	
	class SearchForTool implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (checkSearchByNameRadioButton())
				getClientModelController().getClientController().getSocketOut().println("Search Tool by Name");
			else if (checksearchByIDRadioButton())
				getClientModelController().getClientController().getSocketOut().println("Search Tool by ID");
			else if (checkQuantityRadioButton())
				getClientModelController().getClientController().getSocketOut().println("Check Quantity");
			else if (checkDecreaseQuantityRadioButton())
				getClientModelController().getClientController().getSocketOut().println("Decrease Quantity");
			
			getClientModelController().getClientController().getSocketOut().println(getInventoryView().getSearchParamField().getText());
		}
		
		public boolean checkSearchByNameRadioButton() {
			return getInventoryView().getSearchByNameRadioButton().isSelected();
		}
		
		public boolean checksearchByIDRadioButton() {
			return getInventoryView().getSearchByIDRadioButton().isSelected();
		}
		
		public boolean checkQuantityRadioButton() {
			return getInventoryView().getCheckQuantityRadioButton().isSelected();
		}
		
		public boolean checkDecreaseQuantityRadioButton() {
			return getInventoryView().getDecreaseQuantityRadioButton().isSelected();
		}
	}
	
	public void updateResultsAreaWithTools(ArrayList<Tool> tools) {
		this.clearResultsPane();
		for (Tool t: tools)
			this.getInventoryView().getResultsArea().append(t.toString() + "\n");
		this.clearSearchParamField();
	}
	
	public void updateResultsAreaWithQuantity(ArrayList<Tool> tools) {
		this.clearResultsPane();
		for (Tool t: tools)
			this.getInventoryView().getResultsArea().append("".format("The remaining quantity of %s is %d\n", 
																		   t.getToolName(), t.getQuantity()));
		this.clearSearchParamField();
	}
	
	public void updateResultsAreaWithDecreaseQuantityNotification(String s) {
		this.clearResultsPane();
		this.getInventoryView().getResultsArea().append(s + "\n");
		this.clearSearchParamField();
	}
	
	public void clearResultsPane() {
		this.getInventoryView().getResultsArea().setText("");
	}
	
	public void clearSearchParamField() {
		this.getInventoryView().getSearchParamField().setText("");
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
