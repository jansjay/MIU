package prob2B;
import java.security.InvalidParameterException;

public class OrderLine {
	private Order order;
	private int orderLineNumber;
	private double price;
	private double quantity;
	
	OrderLine(Order order, int orderLineNumber){
		if(order == null) {
			throw new InvalidParameterException("Invalid parameter. Order cannot be null");
		}
		this.order = order;
		this.orderLineNumber = orderLineNumber;
	}
	
	public Order getOrder() {
		return this.order;
	}
	
	public int getOrderLineNumber() {
		return this.orderLineNumber;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	public static OrderLine createOrderLine(Order order, int orderLineNumber) {
		return new OrderLine(order, orderLineNumber);
	}
	
	public String toString() {
		return "Order Line Number = " + orderLineNumber;
	}
}
