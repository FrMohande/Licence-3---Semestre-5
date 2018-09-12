package courriers.factory.contents.textContent;

import courriers.letters.contents.TextContent;

/**
 * Represent a factory to generate a specific {@link TextContent}
 */
public interface FactoryTextContent {
	TextContent createTextContent();
}
