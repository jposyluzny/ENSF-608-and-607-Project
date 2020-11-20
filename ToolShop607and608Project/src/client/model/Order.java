package client.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Order implements Serializable {
	
	private static final long serialVersionUID = 4L;
	private ArrayList <OrderLine> orderLines;
	private int orderID;
	private String date;
	
	public Order() {
		this.setOrderLines(new ArrayList <OrderLine> ());
		this.setOrderID(this.generate5DigitOrderID());
		this.setDate(this.generateCurrentDateTime());
	}
	
	public void createNewOrderLine(int toolID, int supplierID, int orderQuantity) {
        OrderLine orderLine = new OrderLine(this.getOrderID(), toolID, supplierID, orderQuantity);
        this.addOrderLineToList(orderLine);
	}
	
	public boolean checkToCreateNewOrder() {
		if (this.getDate().equals(this.generateCurrentDateTime()))
			return false;
		else
			return true;
	}

    private int generate5DigitOrderID() {
    	Random rand = new Random();
        return rand.nextInt(99999) + 10000;
    }
    
    private String generateCurrentDateTime() {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            return formatter.format(date);
    }
    
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
	
	@Override
	public String toString() {
		return "".format("\nOrder ID: %d\nOrder Date: %s", this.getOrderID(), this.getDate());
	}

}
