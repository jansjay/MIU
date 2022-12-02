package lesson5.labs.prob3;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String name;
	private List<IOrder> orders;
	public Customer(String name) {
		this.name = name;
		orders = new ArrayList<IOrder>();	
	}
	public void addOrder(IOrder order) {
		orders.add(order);
	}
	public String getName() {
		return name;
	}
	public List<IOrder> getOrders() {
		return orders;
	}
}
