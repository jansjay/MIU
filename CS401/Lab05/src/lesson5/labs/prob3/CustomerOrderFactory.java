package lesson5.labs.prob3;

import java.time.LocalDate;

public class CustomerOrderFactory {
	public static IOrder createOrder(Customer cust, LocalDate date) {
		return Order.createOrder(cust, date);
	}
	public static IItem createItem(String name) {
		return Item.createItem(name);
	}
		
}
