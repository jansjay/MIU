package lesson5.labs.prob1.extpackage;

import lesson5.labs.prob1.SingletonClass;

public class TestSingletonClass {

	public static void main(String[] args) {
		SingletonClass key1 = SingletonClass.getSingletonObject();
		SingletonClass key2 = SingletonClass.getSingletonObject(); 
		SingletonClass key3 = SingletonClass.getSingletonObject(); 
		SingletonClass key4 = SingletonClass.getSingletonObject(); 
	}
}
