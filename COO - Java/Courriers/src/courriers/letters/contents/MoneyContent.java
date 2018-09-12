package courriers.letters.contents;

import courriers.utils.UtilsCourriers;

/**
 * Money Content of a letter
 */
public class MoneyContent extends Content {
	protected double money;
	
	/**
	 * Create a money content with a specified amount
	 * @param money the amount of the money content
	 */
	public MoneyContent(double money){
		setMoney(money);
	}
	
	/**
	 * Get the money content
	 * @return The money content
	 */
	public double getMoney() {
		return money;
	}

	/**
	 * Set the money content
	 * @param money The amount of money to set
	 */
	public void setMoney(double money) {
		if( money < 0){
			throw new IllegalArgumentException("The money is negatif.");
		}
		this.money = money;
	}

	@Override
	public String toString() {
		return "a money content (" + UtilsCourriers.format(getMoney()) + "€)";
	}
	
	
}
