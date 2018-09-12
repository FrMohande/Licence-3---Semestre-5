package courriers.factory.letters;

import courriers.factory.letters.abs.AbstractFactoryLetter;
import courriers.factory.letters.abs.DefaultAbstractFactoryLetter;
import courriers.letters.Letter;
import courriers.utils.UtilsCourriers;

/**
 * Represent a factory to generate specified {@link Letter}
 */
public class RandomFactoryLetter implements FactoryLetter {
	
	public static final int RATIO_PROMISSORY_NOTE = 2;
	public static final int RATIO_SIMPLE_LETTER = 10;
	public static final int RATIO_REGISTERED_LETTER = 3;
	public static final int RATIO_URGENT_LETTER = 3;

	public static final int RATIO_GLOBAL = RATIO_PROMISSORY_NOTE + RATIO_SIMPLE_LETTER + RATIO_URGENT_LETTER + RATIO_REGISTERED_LETTER ;


	private AbstractFactoryLetter abstractFactoryLetter;
	
	/**
	 * Create a factory with a specified {@link AbstractFactoryLetter}
	 * @param abstractFactoryLetter The {@link AbstractFactoryLetter} used to generate {@link Letter}
	 */
	public RandomFactoryLetter(AbstractFactoryLetter abstractFactoryLetter) {
		this.abstractFactoryLetter = abstractFactoryLetter;
	}
	
	/**
	 * Create a factory with the default {@link AbstractFactoryLetter}
	 */
	
	public RandomFactoryLetter() {
		this(new DefaultAbstractFactoryLetter());
	}

	@Override
	public Letter<?> createLetter() {
		int nbRandom = UtilsCourriers.RAND.nextInt(RATIO_GLOBAL);
		if (nbRandom < RATIO_PROMISSORY_NOTE) {
			return abstractFactoryLetter.createPromissoryNote();
		}
		if (nbRandom < RATIO_PROMISSORY_NOTE + RATIO_SIMPLE_LETTER) {
			return abstractFactoryLetter.createSimpleLetter();
		}
		if (nbRandom < RATIO_PROMISSORY_NOTE + RATIO_SIMPLE_LETTER + RATIO_REGISTERED_LETTER) {
			return abstractFactoryLetter.createRegisteredLetter();
		}
		return abstractFactoryLetter.createUrgentLetter();
	}
}
