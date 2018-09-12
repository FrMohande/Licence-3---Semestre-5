package courriers.letters;

import courriers.letters.contents.TextContent;
import courriers.letters.decorators.RegisteredLetter;

/**
 * Receipt letter to know if someone has really receive his {@link RegisteredLetter}
 */
public class ReceiptLetter extends SimpleLetter {

	
	/**
	 * Create a {@link TextContent} for a {@link RegisteredLetter}
	 * @param registeredLetter The {@link RegisteredLetter} used to generate to the {@link TextContent}
	 * @return the text content with all informations concatenated
	 */
	protected static TextContent createTextContent(RegisteredLetter<?> registeredLetter) {
		return new TextContent("aknowledgment of receipt for a registered letter whose content is " + registeredLetter.getContent().toString());
	}
	
	/**
	 * Create the {@link ReceiptLetter} for a {@link RegisteredLetter}
	 * @param registeredLetter The {@link RegisteredLetter} used to the {@link ReceiptLetter}
	 */
	public ReceiptLetter(RegisteredLetter<?> registeredLetter) {
		super(COST_SIMPLE_LETTER, registeredLetter.getReceiver(), registeredLetter.getSender(), createTextContent(registeredLetter));
	}
}
