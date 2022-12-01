package lesson3.labs.prob2.extpackage;

import lesson3.labs.prob2.Apartment;
import lesson3.labs.prob2.Building;

public class Main {
	public static void main(String[] args) {
		// Assumptions
		// Building can exist without Apartments
		// Apartment should exist only inside a Building
		
		Building building = Building.createBuilding(100);
		Apartment.createNewApartment(building, 100);
		Apartment.createNewApartment(building, 200);
		Apartment.createNewApartment(building, 300);
		Apartment.createNewApartment(building, 400);
		
		System.out.println("Profit is: " + building.getProfit());
	}	
	
}
