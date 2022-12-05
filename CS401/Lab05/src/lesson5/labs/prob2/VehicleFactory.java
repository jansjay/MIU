package lesson5.labs.prob2;

public class VehicleFactory {
	public static Vehicle getVehicle(String v) {
		if(v.equalsIgnoreCase("bus")) {
			return new Bus();
		} else if(v.equalsIgnoreCase("car")) {
			return new Car();
		} else if(v.equalsIgnoreCase("electriccar")) {
			return new ElectricCar();
		} 
		else {
			return new Truck();
		}
		
	}
}
