package courriers.factory.contents.textContent;

import courriers.letters.contents.TextContent;
import courriers.utils.UtilsCourriers;

/**
 * Represent a factory to generate a random content
 */
public class RandomFactoryTextContent implements FactoryTextContent{
	
	private static final char[] ALPHABET = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 
											 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', '\n'};
	
	public static final int DEFAULT_NB_CHARACTER = 10;
	protected int nbCharacter;
	

	public RandomFactoryTextContent() {
		this(DEFAULT_NB_CHARACTER);
	}
	
	public RandomFactoryTextContent(int nbCharacter) {
		this.nbCharacter = nbCharacter;
	}
	
	@Override
	public TextContent createTextContent() {
		StringBuilder result = new StringBuilder();
		for(int i=0; i < nbCharacter; ++i) {
			result.append(ALPHABET[UtilsCourriers.RAND.nextInt(ALPHABET.length)]);
		}
		return new TextContent(result.toString());
	}
	
}
