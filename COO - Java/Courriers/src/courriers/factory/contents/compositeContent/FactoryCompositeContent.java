package courriers.factory.contents.compositeContent;

import courriers.letters.contents.CompositeContent;

/**
 * Represent a factory to generate a specific case of {@link CompositeContent}
 */
public interface FactoryCompositeContent {
	/**
	 * Create a composite content
	 * @return A list of content as composite
	 */
	CompositeContent createCompositeContent();
}
