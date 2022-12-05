package lesson5.labs.prob2.extpackage;

import lesson5.labs.prob2.Vehicle;
import lesson5.labs.prob2.VehicleFactory;

public class TestVehicle {

	public static void main(String[] args) {
		Vehicle v1 = VehicleFactory.getVehicle("bus");
		v1.startEngine();
		
		Vehicle v2 = VehicleFactory.getVehicle("car");
		v2.startEngine();
		
		Vehicle v3 = VehicleFactory.getVehicle("truck");
		v3.startEngine();
		
		Vehicle v4 = VehicleFactory.getVehicle("electriccar");
		v4.startEngine();
		
	}

}
