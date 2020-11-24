/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.Model;
import java.io.Serializable;

/**
 * This class is used to create OrderLine objects for Tools when they need to be ordered. It implements Serializable, as OrderLine objects
 * need to be passed between the client and server sockets.
 */
public class OrderLine implements Serializable {
	
	private static final long serialVersionUID = 5L;
	private int orderID, toolID, supplierID, orderQuantity;
    
	/**
	 * The OrderLine constructor takes the parameters required to build an OrderLine object.
	 * @param orderID is the ID number of the Order that has been generated
	 * @param toolID is the ID number of the Tool to be ordered
	 * @param supplierID is the ID number of the Supplier that the Tool is to be ordered from
	 * @param orderQuantity is the quantity of the Tool that needs to be ordered
	 */
    public OrderLine(int orderID, int toolID, int supplierID, int orderQuantity) {
        this.setOrderID(orderID);
        this.setToolID(toolID);
        this.setSupplierID(supplierID);
        this.setOrderQuantity(orderQuantity);
    }

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public int getToolID() {
		return toolID;
	}

	public void setToolID(int toolID) {
		this.toolID = toolID;
	}

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}
	
	public String toString() {
		return "".format("\nOrder ID: %d\nTool ID: %d\nSupplier ID: %d\nOrder Quantity: %d", this.getOrderID(), this.getToolID(),
				this.getSupplierID(), this.getOrderQuantity());
	}

}
