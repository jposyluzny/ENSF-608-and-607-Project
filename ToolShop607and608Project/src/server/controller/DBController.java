/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.controller;

/**
 * This class is responsible for working the OrderDBController, CustomerDBController, and InventoryDBController classes.
 *
 */
public class DBController {

	private OrderDBController odbController;
	private CustomerDBController cdbController;
	private InventoryDBController idbController;
	
	/**
	 * The DBController constructor will set the Controller objects passed to it.
	 * @param idbc is the InventoryDBController object
	 * @param cdbc is the CustomerDBController object
	 * @param odbc is the OrderDBController object
	 */
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
