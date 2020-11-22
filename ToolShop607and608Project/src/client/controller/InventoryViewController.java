package client.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import client.view.InventoryGUI;
import server.Model.Tool;

public class InventoryViewController {
	
	private InventoryGUI inventoryView;
	private ClientModelController clientModelController;
	
	public InventoryViewController(ClientModelController clientModelController) {
		this.setInventoryView(new InventoryGUI());
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
			return inventoryView.getSearchByNameRadioButton().isSelected();
		}
		
		public boolean checksearchByIDRadioButton() {
			return inventoryView.getSearchByIDRadioButton().isSelected();
		}
		
		public boolean checkQuantityRadioButton() {
			return inventoryView.getCheckQuantityRadioButton().isSelected();
		}
		
		public boolean checkDecreaseQuantityRadioButton() {
			return inventoryView.getDecreaseQuantityRadioButton().isSelected();
		}
	}
	
	public void updateResultsAreaWithAllTools(ArrayList<Tool> arr) {
		this.clearResultsPane();
		for (Tool t: arr)
			this.getInventoryView().getResultsArea().append(t.toString() + "\n");
		this.clearSearchParamField();
	}
	
	public void updateResultsAreaWithSingleTool(Tool tool) {
		this.clearResultsPane();
		this.getInventoryView().getResultsArea().append(tool.toString() + "\n");
		this.clearSearchParamField();
	}
	
	public void updateResultsAreaWithQuantity(Tool tool) {
		this.clearResultsPane();
		this.getInventoryView().getResultsArea().append("".format("The remaining quantity of %s is %d\n", tool.getToolName(), tool.getQuantity()));
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
