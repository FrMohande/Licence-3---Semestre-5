package courriers;

import java.util.ArrayList;
import java.util.List;

import courriers.city.City;
import courriers.city.Inhabitant;
import courriers.factory.city.FactoryCity;
import courriers.factory.letters.FactoryLetter;
import courriers.factory.letters.RandomFactoryLetter;
import courriers.letters.Letter;
import courriers.utils.UtilsCourriers;

public class Simulate {

	protected int currentDay = 1;
	protected int nbDay;
	
	protected int minNbRandomInhabitant;
	protected int maxNbRandomInhabitant;

	public static final int DEFAULT_MIN_NB_RANDOM_INHABITANT = 3;
	public static final int DEFAULT_MAX_NB_RANDOM_INHABITANT = 7;
	public static final int DEFAULT_NB_DAY = 6;
	
	protected int nbRandomInhabitants;
	protected List<Inhabitant> randomReceiverInhabitants;
	protected List<Inhabitant> randomSenderInhabitants;
 
	protected City city;
	
	private boolean isFirstDay;
	
	public Simulate(FactoryCity factoryCity) {
		this(DEFAULT_NB_DAY, DEFAULT_MIN_NB_RANDOM_INHABITANT, DEFAULT_MAX_NB_RANDOM_INHABITANT, factoryCity);
	}
	
	public Simulate(int nbDay, int minNbRandomInhabitant, int maxNbRandomInhabitant, FactoryCity factoryCity) {
		this.nbDay = nbDay;
		this.minNbRandomInhabitant = minNbRandomInhabitant;
		this.maxNbRandomInhabitant = maxNbRandomInhabitant; 
		this.city = factoryCity.createCity();
	}
	
	public int getNbDay() {
		return nbDay;
	}

	public int getCurrentDay() {
		return currentDay;
	}
	
	public void run() {
		System.out.println(messageForNDayMail());
		isFirstDay = true;
		while (currentDay < nbDay || city.hasMailToDistribute()) {
			System.out.println(messageForNDay());
			distributeLetters();
			if (currentDay < nbDay) {
				initRandomInhabitants();
				sendInhabitants();
			}
			receiveInhabitants();
			nextDay();
		}
	}
	
	protected String messageForNDayMail() {
		return "Mailing letters for " + nbDay + " days";
	}
	
	protected void initRandomInhabitants() {
		nbRandomInhabitants = UtilsCourriers.randInt(minNbRandomInhabitant, maxNbRandomInhabitant);
		randomSenderInhabitants = new ArrayList<>();
		randomReceiverInhabitants = new ArrayList<>();
		for (int i = 0; i < nbRandomInhabitants; i++) {
			randomSenderInhabitants.add(getRandomSenderInhabitant());
			randomReceiverInhabitants.add(getRandomReceiverInhabitant(randomSenderInhabitants.get(i)));
		}
	}
	
	protected void sendInhabitants() {
		FactoryLetter factoryLetter = new RandomFactoryLetter();
		for (int i = 0; i < nbRandomInhabitants; i++) {
			try {
				Letter<?> letter = factoryLetter.createLetter();
				letter.setSender(randomSenderInhabitants.get(i));
				letter.setReceiver(randomReceiverInhabitants.get(i));
				city.sendLetter(letter);
			} catch (IllegalArgumentException e) { }
		}
		
	}
	
	protected void receiveInhabitants() {
		if(isFirstDay) {
			return;
		}
		for(Inhabitant inhabitant : city.getInhabitants()) {
			inhabitant.showLetters();
		}
	}
	
	protected void distributeLetters() {
		if(isFirstDay) {
			return;
		}
		city.distributeLetters();
	}
	
	protected String messageForNDay() {
		return "**********************************************\nDay " + currentDay;
	}

	protected Inhabitant getRandomReceiverInhabitant(Inhabitant sender) {
		Inhabitant receiver = null;
		do {
			receiver = city.getInhabitants().get(UtilsCourriers.RAND.nextInt(city.getInhabitants().size()));
		} while (receiver.equals(sender));
		return receiver;
	}
	
	protected Inhabitant getRandomSenderInhabitant() {
		return city.getInhabitants().get(UtilsCourriers.RAND.nextInt(city.getInhabitants().size()));
	}
	
	protected void nextDay() {
		++currentDay;
		isFirstDay = false;
	}
	
}
