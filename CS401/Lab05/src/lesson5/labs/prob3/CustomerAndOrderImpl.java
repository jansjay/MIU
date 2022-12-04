package lesson5.labs.prob3;

final class CustomerAndOrderImpl implements CustomerAndOrder {
	private Customer cust;
	private Order order;
	
	CustomerAndOrderImpl(Customer cust, Order ordr) {
		this.cust = cust;
		this.order = ordr;
	}
	
	@Override
	public Customer getCustomer() {
		
		return this.cust;
	}

	@Override
	public Order getOrder() {
		
		return this.order;
	}

}
