package courriers.utils;

import java.util.Random;

public class UtilsCourriers {
	
	public static final Random RAND = new Random();
	
	public static int randInt(int min, int max) {
		return min + RAND.nextInt((max - min));
	}
	
	public static double randDouble(double min, double max) {
		return min + (max - min) * RAND.nextDouble();
	}
	
	public static String format(double value) {
		return String.format("%.2f", value);
	}
}
