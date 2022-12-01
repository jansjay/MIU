package lesson3.labs.prob2;

import java.util.List;

public class Building {
	private List<Apartment> apartments;
	private double maintenanceCost = 0;
	private Building(List<Apartment> apartments, double maintenanceCost){
		if(apartments == null || apartments.size() == 0) {
			throw new IllegalArgumentException("Apartments cannot be null and should contain at least one Apartment.");
		}
		if(maintenanceCost <= 0) {
			throw new IllegalArgumentException("maintenanceCost should be greater than 0.");
		}		
		this.apartments = apartments;
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
	public double getProfit() {
		double profit = maintenanceCost * -1;
		for(Apartment apartment : apartments) {
			profit += apartment.getRent();
		}
		return profit;
	}
	public static Building createBuilding(List<Apartment> apartments, double maintenanceCost) {
		return new Building(apartments, maintenanceCost);
	}
}
