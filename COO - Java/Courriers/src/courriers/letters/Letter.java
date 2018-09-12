package courriers.letters;

import courriers.city.Inhabitant;
import courriers.letters.contents.Content;

/**
 * This class represent the base of a letter
 * @param <T> The type of the content used in the letter
 */
public abstract class Letter<T extends Content> {
	
	private double cost;
	
	protected Inhabitant sender;
	protected Inhabitant receiver;
	
	protected T content;
	
	/**
	 * Create a letter with a cost
	 * @param cost The cost of the letter
	 */
	public Letter(double cost){
		this(cost, null);
	}
	
	/**
	 * Create a letter with a cost and a content
	 * @param cost The cost of the letter
	 * @param content The content of the letter
	 */
	public Letter(double cost, T content){
		this(cost, null, null, content);
	}
	
	/**
	 * Create a letter with a cost, a content, the sender and the receiver
	 * @param cost The cost of the letter
	 * @param sender The sender of the letter
	 * @param receiver The receiver of the letter
	 * @param content The content of the letter
	 */
	public Letter(double cost, Inhabitant sender, Inhabitant receiver, T content){
		setCost(cost);
		this.sender = sender;
		this.receiver = receiver;
		this.content = content;
	}
	
	/**
	 * Check if the content is null of not
	 * @return {@link NullPointerException} if there is no content, false in the other case
	 */
	protected boolean isNullForContent(){
		if(getContent() == null){
			throw new NullPointerException("The content is null.");
		}
		return false;
	}
	
	/**
	 * Check if there is a receiver
	 * @return {@link NullPointerException} if there is no receiver, false in the other case
	 */
	public boolean isNullForReceiver(){
		if(getReceiver() == null){
			throw new NullPointerException("The receiver is null.");
		}
		return false;
	}
	
	/**
	 * Check if there is a sender
	 * @return {@link NullPointerException} if there is no sender, false in the other case
	 */
	public boolean isNullForSender(){
		if(getSender() == null){
			throw new NullPointerException("The sender is null.");
		}
		return false;
	}
	

	/**
	 * Execute the operation of the letter
	 */
	public void operation(){
		if(!(isNullForContent() && isNullForReceiver() && isNullForSender())){
			System.out.println(messageReceiver());
			action();
		}
	}
	
	/**
	 * Display the name of the receiver
	 * @return The name of the receiver
	 */
	protected String messageReceiver() {
		return "<- " + getReceiver().getName() + " receives " + toString() + " from " + getSender().getName();
	}
	
	@Override
	public abstract String toString();
	
	protected abstract void action();
	
	/**
	 * Return the sender
	 * @return The sender of the letter
	 */
	public Inhabitant getSender() {
		return sender;
	}

	/**
	 * Set a sender
	 * @param sender The sender to set
	 */
	public void setSender(Inhabitant sender) {
		this.sender = sender;
	}

	/**
	 * Return the receiver
	 * @return The receiver of the letter
	 */
	public Inhabitant getReceiver() {
		return receiver;
	}
	
	/**
	 * Set the receiver of the letter
	 * @param receiver The receiver of the letter
	 */
	public void setReceiver(Inhabitant receiver) {
		this.receiver = receiver;
	}
	
	/**
	 * Return the cost of the letter
	 * @return The cost of the letter
	 */
	public double getCost() {
		return cost;
	}
	
	/**
	 * Set the cost of the letter
	 * @param cost The cost of the letter
	 */
	public void setCost(double cost) {
		if( cost < 0)
			throw new IllegalArgumentException("The cost is negatif.");
		this.cost = cost;
	}

	/**
	 * Return the content of the letter
	 * @return The content of the letter
	 */
	public T getContent() {
		return content;
	}

	/**
	 * Set the content of the letter
	 * @param content The content of the letter
	 */
	public void setContent(T content) {
		this.content = content;
	}
}
