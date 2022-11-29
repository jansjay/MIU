package prob2B.extpackage;

import prob2B.Order;
import prob2B.OrderLine;

public class Main {
	public static void main(String args[])
	{
		Order order = Order.createOrder(1);
		
		OrderLine orderLine1 = OrderLine.createOrderLine(order, 1);
		order.addOrderLine(orderLine1);
		
		OrderLine orderLine2 = OrderLine.createOrderLine(order, 2);
		order.addOrderLine(orderLine2);		
		
		System.out.println("Order Line to Order association - Using an OrderLine object");
		System.out.println(orderLine1.getOrder());
		System.out.println(orderLine2.getOrder());
		
		System.out.println("Order to OrderLine association - Using an Order object");
		System.out.println(order.getOrderLine(1));
		System.out.println(order.getOrderLine(2));
	}
}
