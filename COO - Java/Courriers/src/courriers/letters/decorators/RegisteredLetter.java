package courriers.letters.decorators;

import courriers.letters.Letter;
import courriers.letters.ReceiptLetter;

public class RegisteredLetter<L extends Letter<?> & Decorable> extends DecoratorLetter<L> {
	public static final double EXTRA_COST = 15;
	
	/**
	 * Generate a decorator for a {@link Letter} the make it registered
	 * @param letter The type of letter
	 */
	public RegisteredLetter(L letter) {
		super(EXTRA_COST, letter);
	}

	@Override
	protected void action() {
		ReceiptLetter receipt = new ReceiptLetter(this);
		getReceiver().getCity().sendLetter(receipt);
		letter.operation();
	}
	
	@Override
	public double getCost() {
		return super.getCost() + letter.getCost();
	}
	
	@Override
	public String toString() {
		return "a registered letter whose content is " + letter.toString();
	}

}
