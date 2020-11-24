/**
 * Date: November 23, 2020
 * @author Patrick Pickard, Josh Posyluzny
 * Project: 607/608 Joint Project
 */

package server.Model;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * This class is used to create Order objects for Tools that need to be ordered. It implements Serializable, as Order objects need to be
 * passed between the client and server sockets.
 */
public class Order implements Serializable {
	
	private static final long serialVersionUID = 4L;
	private ArrayList <OrderLine> orderLines;
	private int orderID;
	private String date;
	
	/**
	 * The Order constructor builds a new ArrayList to hold OrderLine objects, generates a random 5 digit order ID for the Order, and gets
	 * the current date to assign to the Order.
	 */
	public Order() {
		this.setOrderLines(new ArrayList <OrderLine> ());
		this.setOrderID(this.generate5DigitOrderID());
		this.setDate(this.generateCurrentDateTime());
	}
	
	/**
	 * The createNewOrderLine() method takes the parameters necessary to create an OrderLine object, builds the OrderLine, and then adds
	 * the OrderLine to the ArrayList of OrderLines.
	 * @param toolID is the ID number of the Tool to be ordered
	 * @param supplierID is the ID number of the Supplier, which the Tool will be ordered from
	 * @param orderQuantity is the amount of the Tool that needs to be ordered
	 */
	public void createNewOrderLine(int toolID, int supplierID, int orderQuantity) {
        this.addOrderLineToList(new OrderLine(this.getOrderID(), toolID, supplierID, orderQuantity));
	}
	
	/**
	 * The checkToCreateNewOrder() method will check whether the current Order's date is today's date. If it is, true is returned. If not,
	 * false is returned.
	 */
	public boolean checkToCreateNewOrder() {
		return this.getDate().equals(this.generateCurrentDateTime());
	}
	
	/**
	 * The clearList() method will clear the orderLines ArrayList of entries.
	 */
	public void clearList() {
		this.getOrderLines().clear();
	}

	/**
	 * The generate4DigitOrderID() method will generate a random ID number for the Order that is created.
	 */
    private int generate5DigitOrderID() {
    	Random rand = new Random();
        return rand.nextInt(99999) + 10000;
    }
    
    /**
     * The generateCurrentDateTime() method will get today's date in the format of yyyy-MM-dd.
     * @return
     */
    private String generateCurrentDateTime() {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    	return formatter.format(new Date());
    }
    
    /**
     * The addOrderLineToList() method will add an OrderLine object to the orderLines ArrayList.
     * @param orderLine is the OrderLine object to be added
     */
    public void addOrderLineToList(OrderLine orderLine) {
    	this.getOrderLines().add(orderLine);
    }
    
	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ArrayList <OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(ArrayList <OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	
	public String toString() {
		return "".format("\nOrder ID: %d\nOrder Date: %s", this.getOrderID(), this.getDate());
	}

}
