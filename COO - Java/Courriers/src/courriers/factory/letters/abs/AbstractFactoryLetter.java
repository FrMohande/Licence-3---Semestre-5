package courriers.factory.letters.abs;

import courriers.letters.ComplexLetter;
import courriers.letters.Letter;
import courriers.letters.PromissoryNote;
import courriers.letters.SimpleLetter;
import courriers.letters.decorators.RegisteredLetter;
import courriers.letters.decorators.UrgentLetter;

/**
 * Represent a factory to generate specific {@link Letter}
 */
public interface AbstractFactoryLetter {
	
	/**
	 * Generate a {@link PromissoryNote}
	 * @return a {@link PromissoryNote}
	 */
	public PromissoryNote createPromissoryNote();

	/**
	 * Generate a {@link SimpleLetter}
	 * @return a {@link SimpleLetter}
	 */
	public SimpleLetter createSimpleLetter();
	
	/**
	 * Generate a {@link ComplexLetter}
	 * @return a {@link ComplexLetter}
	 */
	public ComplexLetter createComplexLetter();
	
	public RegisteredLetter<?> createRegisteredLetter();
	
	public UrgentLetter<?> createUrgentLetter();
}
