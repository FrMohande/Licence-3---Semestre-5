package courriers.city;

/**
 * This class represent an inhabitant
 */
public class Inhabitant {

	String name;
	protected City city;
	protected BankAccount bankAccount;
	protected Postbox postbox;
	
	/**
	 * Create an inhabitant with a name and the city where he lives
	 * @param name his name
	 * @param city the city where he lives
	 */
	public Inhabitant(String name, City city) {
		this(name, city, 0);
	}

	/**
	 * Create an inhabitant with a name, a city, and an amount in his bank account
	 * @param name his name
	 * @param city his city
	 * @param amount the amount in his bank account
	 */
	public Inhabitant(String name, City city, double amount) {
		super();
		this.name = name;
		this.city = city;
		this.bankAccount = new BankAccount(this, amount);
		this.postbox = new Postbox();
	}
	
	/**
	 * Credit the bank account
	 * @param amount the amount to be credited
	 */
	public void credit(double amount){
		bankAccount.credit(amount);
	}
	
	/**
	 * Debit the bank account
	 * @param amount the amount to be debited
	 * @return True if it works, false in other case
	 */
	public boolean debit(double amount){
		return bankAccount.debit(amount);
	}
	
	public boolean debitWithMessage(double amount) {
		return bankAccount.debitWithMessage(amount);
	}
	
	public void creditWithMessage(double amount){
		bankAccount.creditWithMessage(amount);
	}
	
	/**
	 * Return the name of the inhabitant
	 * @return name of the inhabitant
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the city of the inhabitant
	 * @return the city of the inhabitant
	 */
	public City getCity() {
		return city;
	}

	/**
	 * Return the bank account of the inhabitant
	 * @return the bank account of the inhabitant
	 */
	public BankAccount getBankAccount() {
		return bankAccount;
	}

	/**
	 * Return the postbox of the inhabitant
	 * @return the postbox of the inhabitant
	 */
	public Postbox getPostbox() {
		return postbox;
	}
	
	/**
	 * Display all letters availables in the postbox
	 */
	public void showLetters() {
		while(!postbox.isEmpty()) {
			postbox.takeFirstLetter().operation();
		}
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
}
