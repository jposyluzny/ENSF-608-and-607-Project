package server.controller;

public class DBController {
	
	private OrderDBController odbController;
	private CustomerDBController cdbController;
	private InventoryDBController idbController;
	
	public DBController(InventoryDBController idbc, CustomerDBController cdbc, OrderDBController odbc) {
		this.setIdbController(idbc);
		this.setCdbController(cdbc);
		this.setOdbController(odbc);
	}

	public OrderDBController getOdbController() {
		return odbController;
	}

	public void setOdbController(OrderDBController odbController) {
		this.odbController = odbController;
	}

	public CustomerDBController getCdbController() {
		return cdbController;
	}

	public void setCdbController(CustomerDBController cdbController) {
		this.cdbController = cdbController;
	}

	public InventoryDBController getIdbController() {
		return idbController;
	}

	public void setIdbController(InventoryDBController idbController) {
		this.idbController = idbController;
	}

}
