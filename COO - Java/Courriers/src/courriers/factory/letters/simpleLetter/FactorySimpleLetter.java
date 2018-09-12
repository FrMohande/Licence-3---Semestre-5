package courriers.factory.letters.simpleLetter;

import courriers.letters.SimpleLetter;

/**
 * Represent a factory to generate specific {@link SimpleLetter}
 */
public interface FactorySimpleLetter {
	/**
	 * Generate a {@link SimpleLetter}
	 * @return The {@link SimpleLetter} generated
	 */
	SimpleLetter createSimpleLetter();
}
