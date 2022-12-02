package lesson5.labs.prob3.extpackage;

import java.time.LocalDate;

import lesson5.labs.prob3.Customer;
import lesson5.labs.prob3.CustomerOrderFactory;
import lesson5.labs.prob3.IOrder;

public class Main {
	public static void main(String[] args) {
		Customer cust = new Customer("Bob");
		IOrder order = CustomerOrderFactory.createOrder(cust, LocalDate.now());
		order.addItem("Shirt");
		order.addItem("Laptop");

		order = CustomerOrderFactory.createOrder(cust, LocalDate.now());
		order.addItem("Pants");
		order.addItem("Knife set");

		System.out.println(cust.getOrders());
	}
}

		
