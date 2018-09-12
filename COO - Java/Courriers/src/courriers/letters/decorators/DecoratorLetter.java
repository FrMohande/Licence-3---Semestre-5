package courriers.letters.decorators;

import courriers.city.Inhabitant;
import courriers.letters.Letter;
import courriers.letters.contents.Content;

/**
 * Class representing a specific decorator for a {@link Letter}
 * @param <L> A letter that extends {@link Decorable}
 */
public abstract class DecoratorLetter<L extends Letter<?> & Decorable> extends Letter<Content> {

	protected Letter<?> letter;

	/**
	 * Generate a decorator for a {@link Letter}
	 * @param cost The cost of the letter
	 * @param letter The type of letter
	 */
	public DecoratorLetter(double cost, L letter) {
		super(cost);
		this.letter = letter;
	}
	
	@Override
	public Inhabitant getReceiver() {
		return letter.getReceiver();
	}

	@Override
	public Inhabitant getSender() {
		return letter.getSender();
	}
	
	@Override
	public Content getContent() {
		return letter.getContent();
	}
	
	@Override
	public void setSender(Inhabitant sender) {
		letter.setSender(sender);
	}
	
	@Override
	public void setReceiver(Inhabitant receiver) {
		letter.setReceiver(receiver);
	}
}
