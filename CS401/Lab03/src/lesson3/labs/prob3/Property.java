package lesson3.labs.prob3;

public abstract class Property {
	private double rent;
	public Property(double rent) {
		this.rent = rent;
	}
	public double getRent() {
		return rent;
	}

	public void setRent(double rent) {
		this.rent = rent;
	}
	public abstract double computeRent();
}
