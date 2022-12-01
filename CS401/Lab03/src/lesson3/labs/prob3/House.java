package lesson3.labs.prob3;



public class House  extends Property{
	private double lotSize;
	private Address address;
	public Address getAddress() {
		return address;
	}
	public House(Address address, double lotSize) {
		super(0.1 * lotSize);
		this.address = address;
		this.lotSize = lotSize;
	}

	@Override
	public double computeRent(){
		return this.getRent();
	}
	public double getLotSize() {
		return lotSize;
	}
}
