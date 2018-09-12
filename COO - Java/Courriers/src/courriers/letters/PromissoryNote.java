package courriers.letters;

import courriers.city.Inhabitant;
import courriers.letters.contents.MoneyContent;
import courriers.letters.decorators.Decorable;

/**
 * This class represent a promissory note
 * This kind of letter contains an amount to give to an inhabitant
 */
public class PromissoryNote extends Letter<MoneyContent> implements Decorable{
	
	private static final double FIX_COST = 1;
	private static final double COEFF_COST = 0.01;

	/**
	 * Create a {@link Letter} with a money content
	 * @param moneyContent The amount of money of the promissory note
	 */
	public PromissoryNote(MoneyContent moneyContent){
		this(null, null, moneyContent);
	}
	
	/**
	 * Create a {@link Letter} with a money content, a sender and a receiver
	 * @param sender The sender of the letter
	 * @param receiver The receiver of the letter
	 * @param moneyContent The money content of the letter
	 */
	public PromissoryNote(Inhabitant sender, Inhabitant receiver,MoneyContent moneyContent){
		super(42, sender, receiver, moneyContent);
		setCost(COEFF_COST * content.getMoney() + FIX_COST);
	}	
	
	@Override
	protected void action() {
		getSender().debitWithMessage(getContent().getMoney());
		getReceiver().creditWithMessage(getContent().getMoney());
		ThanksLetter thanksLetter = new ThanksLetter(this);
		getReceiver().getCity().sendLetter(thanksLetter);
	}

	@Override
	public String toString() {
		return "a promissory letter whose content is " + content.toString();
	}

}
