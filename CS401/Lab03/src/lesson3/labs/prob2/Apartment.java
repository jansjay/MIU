package lesson3.labs.prob2;

public class Apartment {
	private double rent;
	private Building building;
	private Apartment(Building building, double rent) {
		if(building == null) {
			throw new IllegalArgumentException("Building cannot be null.");
		}		
		if(rent <= 0) {
			throw new IllegalArgumentException("Rent should be greater than 0.");
		}
		this.building = building;
		this.rent = rent;
		this.building.addApartment(this);
	}	
	public double getRent() {
		return rent;
	}
	public void setRent(double rent) {
		this.rent = rent;
	}
	public static Apartment createNewApartment(Building building, double rent) {
		return new Apartment(building, rent);
	}

	public Building getBuilding() {
		return building;
	}
}
