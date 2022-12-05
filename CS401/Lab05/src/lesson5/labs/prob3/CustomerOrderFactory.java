package lesson5.labs.prob3;

import java.time.LocalDate;

public final class CustomerOrderFactory {
	
	public static CustomerAndOrder createCustomerAndOrder(String name, LocalDate orderDate) {
		Customer cust = new Customer(name);
		Order order = Order.newOrder(cust, LocalDate.now());
		CustomerAndOrder custOrder = new CustomerAndOrderImpl(cust, order);
		return custOrder;
	}
	
	public static CustomerAndOrder createOrder(Customer cust, LocalDate orderDate) {
		Order order = Order.newOrder(cust, LocalDate.now());
		CustomerAndOrder custOrder = new CustomerAndOrderImpl(cust, order);
		return custOrder;
	}
	
	public static OrderAndItem createItem(Order order, String itemName) {
		Item item = Item.newItem(order, itemName);
		OrderAndItem orderAndItem = new OrderAndItemImpl(order, item);
		return orderAndItem;
	}

}
