package prob2B;

import java.security.InvalidParameterException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


public class Order {
	private List<OrderLine> orderLines;
	private int orderNumber;
	private Date orderDate;
	Order(int orderNumber) {
		this.orderNumber = orderNumber;
		this.orderLines = new ArrayList<>();
	}
	
	public static Order createOrder(int orderNumber) {
		return new Order(orderNumber);
	}
	
	public void addOrderLine(OrderLine orderLine) {
		if(orderLine == null) {
			throw new InvalidParameterException("Invalid parameter. OrderLine cannot be null");
		}
		Order order = orderLine.getOrder();
		if( order != null && order != this) {
			throw new InvalidParameterException("Invalid parameter. Order has a different order attached already");
		}
		orderLines.add(orderLine);		
	}
	
	public OrderLine getOrderLine(int orderLineNumber) {
		for(OrderLine line : orderLines) {
			if(line.getOrderLineNumber() == orderLineNumber) {
				return line;
			}
		}
		return null;
	}
	
	public Date getOrderDate() {
		return orderDate;
	}
	
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public String toString() {
		String toReturn = "Order  Number = " + Integer.toString(this.orderNumber) + " , No of Order Lines = " + orderLines.size();
		for(OrderLine line: orderLines)
		{
			toReturn += "\n" + line.toString();
		}
		return toReturn;
	}
}
