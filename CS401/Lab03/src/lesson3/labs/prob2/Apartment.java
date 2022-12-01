package lesson3.labs.prob2;

public class Apartment {
	private double rent;
	private Apartment(double rent) {
		if(rent <= 0) {
			throw new IllegalArgumentException("Rent should be greater than 0.");
		}		
		this.rent = rent;
	}
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public static Apartment createNewApartment(double rent) {
		return new Apartment(rent );
	}
}
