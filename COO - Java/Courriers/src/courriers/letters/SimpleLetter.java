package courriers.letters;

import courriers.city.Inhabitant;
import courriers.letters.contents.TextContent;
import courriers.letters.decorators.Decorable;

/**
 * Represent a simpleLetter
 */
public class SimpleLetter extends Letter<TextContent> implements Decorable {
	public static final int  COST_SIMPLE_LETTER = 1;
	
	/**
	 * Create a simple letter
	 * @param textContent The content to set in a {@link SimpleLetter}
	 */
	public SimpleLetter(TextContent textContent){
		this(COST_SIMPLE_LETTER, textContent);
	}
	
	/**
	 * Create a simple letter with a specified cost
	 * @param cost The cost of the {@link SimpleLetter}
	 * @param textContent the {@link TextContent} of the {@link SimpleLetter}
	 */
	public SimpleLetter(double cost, TextContent textContent) {
		super(cost, textContent);
	}
	
	/**
	 * Create a simple letter with a specified cost, a sender, a receiver and a {@link TextContent}
	 * @param cost The cost of the {@link SimpleLetter}
	 * @param sender The Sender of the {@link SimpleLetter}
	 * @param receiver The receiver of the {@link SimpleLetter}
	 * @param textContent The {@link TextContent} of the {@link SimpleLetter}
	 */
	public SimpleLetter(double cost, Inhabitant sender, Inhabitant receiver, TextContent textContent){
		super(cost, sender, receiver, textContent);
	}
	
	@Override
	protected void action() {
		
	}

	@Override
	public String toString() {
		return "a simple letter whose content is " + content.toString();
	}

}