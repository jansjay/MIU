package lesson5.labs.prob2.extpackage;

import java.util.ArrayList;
import java.util.List;

import lesson5.labs.prob2.Vehicle;
import lesson5.labs.prob2.VehicleFactory;

public class TestVehicle {

	public static void main(String[] args) {
		String[] vehicleTypes = {"bus", "car", "truck", "electriccar"};
		for(String s: vehicleTypes)
		{
			Vehicle vehicle = VehicleFactory.getVehicle(s);
			vehicle.startEngine();
		}		
	}

}
