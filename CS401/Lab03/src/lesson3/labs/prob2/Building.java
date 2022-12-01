package lesson3.labs.prob2;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Building {
	private List<Apartment> apartments;
	private double maintenanceCost = 0;
	private Building(double maintenanceCost){
		if(maintenanceCost <= 0) {
			throw new IllegalArgumentException("maintenanceCost should be greater than 0.");
		}		
		this.apartments = new ArrayList<>();
		this.maintenanceCost = maintenanceCost;
	}
	public double getMaintenanceCost() {
		return maintenanceCost;
	}
	public void setMaintenanceCost(List<Apartment> apartments, double maintenanceCost) {
		this.maintenanceCost = maintenanceCost;
	}
	public List<Apartment> getApartments() {
		return apartments;
	}
	public void addApartment(Apartment apartment) {
		if(apartment.getBuilding() != this) {
			throw new InvalidParameterException("Apartment is owned by another building");
		}
		apartments.add(apartment);
	}
	public double getProfit() {
		double profit = maintenanceCost * -1;
		for(Apartment apartment : apartments) {
			profit += apartment.getRent();
		}
		return profit;
	}
	public static Building createBuilding(double maintenanceCost) {
		return new Building(maintenanceCost);
	}
}
