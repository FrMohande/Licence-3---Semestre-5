package courriers;

import courriers.factory.city.DefaultFactoryCity;

public class Main {
	public static void main(String[] args) {
		new Simulate(new DefaultFactoryCity("Lille")).run();
	
	}
}
