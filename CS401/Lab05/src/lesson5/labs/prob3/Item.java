package lesson5.labs.prob3;

public final class Item {
	private String name;
	private Order order;
	Item(Order order, String name) {
		this.name = name;
	}
	static Item newItem(Order order, String name) {
		return new Item(order, name);
	}
	public Order getOrder() {
		return order;
	}
	public String getName() {
		return name;
	}
	@Override
	public String toString() {
		return name;
	}
}
