package courriers.factory.contents.textContent;

import courriers.letters.contents.TextContent;

/**
 * Represent a factory to generate a basic {@link TextContent}
 */
public class DefaultFactoryTextContent implements FactoryTextContent{
	public static final String DEFAULT_TEXT = "Bla Bla";
	protected String text;
	
	public DefaultFactoryTextContent() {
		this(DEFAULT_TEXT);
	}
	
	public DefaultFactoryTextContent(String text) {
		this.text = text;
	}
	
	@Override
	public TextContent createTextContent() {
		return new TextContent(text);
	}

}
