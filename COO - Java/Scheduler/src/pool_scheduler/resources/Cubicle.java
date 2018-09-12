package pool_scheduler.resources;

/**
 * Class representing a Cubicle
 *
 */
public class Cubicle implements Resource {
	
	protected String description;
	
	/**
	 * Create a cubicle with a description
	 * @param description text to display during the action
	 */
	public Cubicle(String description) {
		this.description = description;
	}

	@Override
	public String description() {
		return description;
	}

}
