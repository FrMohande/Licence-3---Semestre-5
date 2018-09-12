package courriers.factory.letters.simpleLetter;

import courriers.factory.contents.textContent.DefaultFactoryTextContent;
import courriers.factory.contents.textContent.FactoryTextContent;
import courriers.letters.PromissoryNote;
import courriers.letters.SimpleLetter;

/**
 * Represent a factory to generate a classic {@link PromissoryNote}
 */
public class DefaultFactorySimpleLetter implements FactorySimpleLetter {
	protected FactoryTextContent factoryTextContent;
	
	/**
	 * Create a factory with a classic {@link SimpleLetter} factory
	 */
	public DefaultFactorySimpleLetter() {
		this(new DefaultFactoryTextContent());
	}
	
	/**
	 * Create a factory with a specified {@link SimpleLetter} factory
	 * @param factoryTextContent The factory used to generate {@link SimpleLetter}
	 */
	public DefaultFactorySimpleLetter(FactoryTextContent factoryTextContent) {
		this.factoryTextContent = factoryTextContent;
	}

	@Override
	public SimpleLetter createSimpleLetter() {
		return new SimpleLetter(factoryTextContent.createTextContent());
	}

}
