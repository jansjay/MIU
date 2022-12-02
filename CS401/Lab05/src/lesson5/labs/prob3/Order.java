package lesson5.labs.prob3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


class Order implements IOrder {
	private LocalDate orderDate;
	private List<IItem> items;
	
	//use a factory method
	private Order(LocalDate orderDate) {
		this.orderDate = orderDate;
		items = new ArrayList<IItem>();	
	}
	static IOrder createOrder(Customer cust, LocalDate date) {
		if(cust == null) throw new NullPointerException("Null customer");
		Order ord = new Order(date);
		cust.addOrder(ord);
		return ord;
	}
	public void addItem(String name){
		items.add(Item.createItem(name));
	}
	@Override
	public String toString() {
		return orderDate + ": " + 
	              items.toString();
	}
}
