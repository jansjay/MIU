package lesson5.labs.prob3;

final class OrderAndItemImpl implements OrderAndItem{

	private Item item;
	private Order order;
	
	OrderAndItemImpl(Order order, Item item){
		this.item = item;
		this.order = order;
	}
	
	@Override
	public Order getOrder() {
		return order;
	}

	@Override
	public Item getItem() {
		return item;
	}

}
