package lesson5.labs.prob3;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


 public final class Order {
	private LocalDate orderDate;
	private List<Item> items;
	
	//use a factory method
	private Order(LocalDate orderDate) {
		this.orderDate = orderDate;
		items = new ArrayList<Item>();	
	}
	
	static Order newOrder(Customer cust, LocalDate date) {
		if(cust == null) throw new NullPointerException("Null customer");
		Order ord = new Order(date);
		cust.addOrder(ord);
		return ord;
	}
	
	public void addItem(Item item){
		items.add(item);
	}
	@Override
	public String toString() {
		return orderDate + ": " + 
	              items.toString();
	}
}
