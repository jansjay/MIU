package lesson5.labs.prob3.extpackage;

import java.time.LocalDate;

import lesson5.labs.prob3.CustomerOrderFactory;
import lesson5.labs.prob3.Customer;
import lesson5.labs.prob3.CustomerAndOrder;
import lesson5.labs.prob3.Order;

public class Main {
	public static void main(String[] args) {
		CustomerAndOrder custOrder1 = CustomerOrderFactory.createCustomerAndOrder("Zaman", LocalDate.now());
		Customer cust = custOrder1.getCustomer();
		Order order = custOrder1.getOrder();
		
		order.addItem(CustomerOrderFactory.createItem(order, "Shirt").getItem());
		order.addItem(CustomerOrderFactory.createItem(order, "Laptop").getItem());
		order.addItem(CustomerOrderFactory.createItem(order, "Pants").getItem());
		order.addItem(CustomerOrderFactory.createItem(order, "Knife set").getItem());
		
		CustomerAndOrder custOrder2 = CustomerOrderFactory.createOrder(cust, LocalDate.now());
		Order order2 = custOrder2.getOrder();
		order2.addItem(CustomerOrderFactory.createItem(order2, "Jacket").getItem());
		order2.addItem(CustomerOrderFactory.createItem(order2, "Mobile").getItem());
		order2.addItem(CustomerOrderFactory.createItem(order2, "Watch").getItem());

		System.out.println(cust.getOrders());
	}
}

		
