package lesson5.labs.prob3sanjaya.extpackage;

import java.time.LocalDate;

import lesson5.labs.prob3sanjaya.Customer;
import lesson5.labs.prob3sanjaya.CustomerOrderFactory;
import lesson5.labs.prob3sanjaya.IOrder;

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

		
