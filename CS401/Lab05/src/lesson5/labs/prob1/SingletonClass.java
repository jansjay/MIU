package lesson5.labs.prob1;

import java.util.Random;

public class SingletonClass {
	private Integer key;
	
	private SingletonClass() {
		key = 1000000 + new Random().nextInt(9999999 - 100000);
	}
	
	private static SingletonClass singletonObj;
	
	public static SingletonClass getSingletonObject() {
		if(singletonObj == null) {
			singletonObj = new SingletonClass();
			System.out.println("Key generated Successfully");
			System.out.println("Your key to activate Aaaaa anti virus is: " + singletonObj.key);
		} else {
			System.out.println("Unsuccessful to produce the key....");
		}
		
		return singletonObj;
	}
}
