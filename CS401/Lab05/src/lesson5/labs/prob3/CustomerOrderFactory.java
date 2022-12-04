package lesson5.labs.prob3;

import java.time.LocalDate;

public class CustomerOrderFactory {
	
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
	

}
