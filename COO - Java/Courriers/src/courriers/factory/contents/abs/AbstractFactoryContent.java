package courriers.factory.contents.abs;

import courriers.letters.contents.CompositeContent;
import courriers.letters.contents.Content;
import courriers.letters.contents.MoneyContent;
import courriers.letters.contents.TextContent;

/**
 * Represent a factory to generate a specific case of {@link Content}
 */
public interface AbstractFactoryContent {
	
	/**
	 * Generate a text content
	 * @return A text content for a letter
	 */
	public TextContent createTextContent();
	
	/**
	 * Generate a money content
	 * @return A money content for a letter
	 */
	public MoneyContent createMoneyContent();
	
	/**
	 * Generate a composite content
	 * @return A composite content for a letter
	 */
	public CompositeContent createCompositeContent();
	
}
