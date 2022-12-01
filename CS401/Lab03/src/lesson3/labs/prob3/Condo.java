package lesson3.labs.prob3;



public class Condo extends Property {
	private Address address;
	private int numberOfFloors;
	public Address getAddress() {
		return address;
	}
	public Condo(Address address, int numberOfFloors) {
		super(400 * numberOfFloors);
		this.address = address;
		this.numberOfFloors = numberOfFloors;
	}
	
	@Override
	public double computeRent(){
		return super.getRent();
	}
	public int getNumberOfFloors() {
		return numberOfFloors;
	}
}
