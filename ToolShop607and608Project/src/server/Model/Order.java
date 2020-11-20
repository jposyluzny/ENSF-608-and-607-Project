package server.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Order {
	
	private ArrayList <OrderLine> orderLines;
	private int orderID;
	private String date;
	
	public Order() {
		orderLines = new ArrayList <OrderLine> ();
		this.setOrderID(this.generate5DigitOrderID());
		this.setDate(this.generateCurrentDateTime());
	}
	
	public void createNewOrder(int toolID, int supplierID, int orderQuantity) {
        OrderLine orderLine = new OrderLine(this.getOrderID(), toolID, supplierID, orderQuantity);
        orderLines.add(orderLine);
	}

    private int generate5DigitOrderID() {
        Random rand = new Random();
        return rand.nextInt(99999)+10000;
    }

    private String generateCurrentDateTime() {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            return formatter.format(date);
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

}
