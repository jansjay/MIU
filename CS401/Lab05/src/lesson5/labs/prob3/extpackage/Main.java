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
		
		order.addItem("Shirt");
		order.addItem("Laptop");
		order.addItem("Pants");
		order.addItem("Knife set");
		
		CustomerAndOrder custOrder2 = CustomerOrderFactory.createOrder(cust, LocalDate.now());
		Order order2 = custOrder2.getOrder();
		order2.addItem("Jacket");
		order2.addItem("Mobile");
		order2.addItem("Watch");

		System.out.println(cust.getOrders());
	}
}

		
