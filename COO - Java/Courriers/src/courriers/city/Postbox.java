package courriers.city;

import java.util.LinkedList;
import java.util.Queue;

import courriers.letters.Letter;

/**
 * Represent a postbox to stock letters
 * This class can be used for an {@link Inhabitant}nt or a {@link City} and everithing else which need to stock letters
 *
 */
public class Postbox {

	protected Queue<Letter<?>> letters = new LinkedList<>();
	
	/**
	 * Add letter in the postbox
	 * @param letter letter to add in the postbox
	 */
	public void addLetter(Letter<?> letter){
		letters.add(letter);
	}
	
	/**
	 * Return the state of the postbox
	 * @return True if the postbox is empty, false in the other case
	 */
	public boolean isEmpty(){
		return letters.isEmpty();
	}
	
	/**
	 * Return all the letter of the postbox
	 * @return The letter of the postbox
	 */
	public Queue<Letter<?>> getLetters() {
		return letters;
	}

	/**
	 * Return the first letter of the postbox
	 * @return The first letter of the postbox
	 */
	public Letter<?> takeFirstLetter() {
		return letters.poll();
	}
	
}
