package lesson3.labs.prob2.extpackage;

import java.util.ArrayList;
import java.util.List;

import lesson3.labs.prob2.Apartment;
import lesson3.labs.prob2.Building;

public class Main {
	public static void main(String[] args) {
		List<Apartment> apartments = new ArrayList<>();
		apartments.add(Apartment.createNewApartment(100));
		apartments.add(Apartment.createNewApartment(200));
		apartments.add(Apartment.createNewApartment(300));
		apartments.add(Apartment.createNewApartment(400));
		
		Building building = Building.createBuilding(apartments, 100);
		System.out.println("Profit is: " + building.getProfit());
	}	
	
}
