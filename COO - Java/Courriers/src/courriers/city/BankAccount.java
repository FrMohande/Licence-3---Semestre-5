package courriers.city;

import courriers.utils.UtilsCourriers;

/**
 * This class Represent a bank account of an {@link Inhabitant}
 *
 */
public class BankAccount {

	private double amount;
	private Inhabitant owner;
	
	/**
	 * Initialize an account with 0€
	 */
	public BankAccount(Inhabitant owner){
		this(owner, 0);
	}
	
	/**
	 * Initialize an account with an amount defined
	 * @param amount The amout of money of the account
	 */
	public BankAccount(Inhabitant owner, double amount) {
		this.amount = amount;
		this.owner = owner;
	}
	
	/**
	 * Verify that the account can do a debit
	 * @param amount the amount wanted to be debited
	 * @return True if the account has enough money, false if it hasn't
	 */
	public boolean hasDebit(double amount){
		return this.amount - amount >= 0;
	}
	
	/**
	 * Debit an amount of the account
	 * @param amount amount wanted to be debited
	 * @return true if it's done, false if not done
	 */
	public boolean debit(double amount){
		if(hasDebit(amount)){
			this.amount -= amount;
			return true;
		}
		return false;
	}
	
	/**
	 * Credit an amount on the account
	 * @param amount amount wanted to be credited
	 */
	public void credit(double amount){
		this.amount += amount;
	}
	
	public void creditWithMessage(double amount) {
		credit(amount);
		System.out.println(messageForCredit(amount));
	}
	
	public boolean debitWithMessage(double amount) {
		boolean debitStatus = debit(amount);
		if(debitStatus) {
			System.out.println(messageForDebit(amount));
		}
		return debitStatus;
	}
	
	/**
	 * Return the amount available on the account
	 * @return The amount avalable in the bank account
	 */
	public double getAmount() {
		return amount;
	}
	
	public Inhabitant getOwner() {
		return owner;
	}
	
	protected void setOwner(Inhabitant owner) {
		this.owner = owner;
	}
		
	protected String messageForDebit(double amount) {
		return "   - " +  UtilsCourriers.format(amount) + " euros are debited from " + getOwner() + " account whose balance is now " + UtilsCourriers.format(getAmount()) + " euros";
	}
	
	protected String messageForCredit(double amount) {
		return "   + " + getOwner() + " account is credited with " + UtilsCourriers.format(amount) + " euros; its balance is now " + UtilsCourriers.format(getAmount()) + " euros";
	}
	

}
