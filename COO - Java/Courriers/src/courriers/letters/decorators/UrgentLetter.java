package courriers.letters.decorators;

import courriers.letters.Letter;

public class UrgentLetter<L extends Letter<?> & Decorable> extends DecoratorLetter<L> {
	
	public static final double EXTRA_COST = 2;
	
	/**
	 * Generate a decorator for a {@link Letter} the make it urgent
	 * @param letter The type of letter
	 */
	public UrgentLetter(L letter) {
		super(EXTRA_COST, letter);

	}

	@Override
	public double getCost() {
		return super.getCost() * letter.getCost();
	}
	
	@Override
	protected void action() {
		letter.operation();
	}
	
	@Override
	public String toString() {
		return "an urgent letter whose content is " + letter.getContent().toString();
	}
}
