package courriers.city;

import java.util.ArrayList;
import java.util.List;

import courriers.letters.Letter;
import courriers.utils.UtilsCourriers;

/**
 * The class represent a city containing inhabitants
 */
public class City {
	
	protected String name;
	protected List<Inhabitant> inhabitants;
	protected Postbox postbox; 
	
	/**
	 * Construct a city
	 * @param name The name of the city
	 */
	public City(String name){
		this.name = name;
		this.inhabitants = new ArrayList<>();
		this.postbox = new Postbox();
	}

	/**
	 * Add inhabitant in the city
	 * @param inHabitant inhabitant to add to the city
	 */
	public void addInhabitants(Inhabitant inHabitant){
		inhabitants.add(inHabitant);
	}
	
	/**
	 * Add letter to the postbox of the city
	 * @param letter the letter to send to the postbox
	 */
	public void sendLetter(Letter<?> letter){
		if(!letter.getSender().debit(letter.getCost())){
			throw new IllegalArgumentException("The sender doesn't have enough money");
		}
		postbox.addLetter(letter);
		System.out.println(messageSender(letter));
	}
	
	/**
	 * Distribute letter available in the postbox
	 */
	public void distributeLetters(){
		int index = -1;
		Letter<?> l;
		while(!postbox.isEmpty()){
			l = postbox.takeFirstLetter();
			index = inhabitants.indexOf(l.getReceiver());

			if(index > -1){
				inhabitants.get(index).getPostbox().addLetter(l);
			}
		}
	}
	
	/**
	 * Verify if there is some letters to distribute
	 * @return True if there is letters, false in the other case
	 */
	public boolean hasMailToDistribute() {
		return !postbox.isEmpty();
	}
	
	/**
	 * Display the name of the sender, the name of the receiver and the cost of the letter
	 * @param letter The letter where we want to know informations
	 * @return the informations of the letter
	 */
	protected String messageSender(Letter<?> letter) {
		return "-> " + letter.getSender().getName() + " mails " + letter.toString() + " to " + letter.getReceiver().getName() + 
						" for a cost of " + UtilsCourriers.format(letter.getCost()) + " euros";
	}

	@Override
	public String toString() {
		return getName();
	}
	/**
	 * Return the name of the city
	 * @return The name of the city
	 */
	public String getName() {
		return name;
	}

	/**
	 * Return the list of the inhabitant
	 * @return the list of the inhabitant
	 */
	public List<Inhabitant> getInhabitants() {
		return inhabitants;
	}

	/**
	 * Return the postbox
	 * @return the postbox
	 */
	public Postbox getPostbox() {
		return postbox;
	}
}