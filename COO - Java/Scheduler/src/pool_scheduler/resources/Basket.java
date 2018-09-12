package pool_scheduler.resources;



/**
 * Class reprensenting a basket
 *
 */
public class Basket implements Resource {
	
	protected String description;
	
	/**
	 * Create a basket with descripton
	 * @param description text to display during the action
	 */
	public Basket(String description){
		this.description = description;
	}
	
	@Override
	public String description() {
		return description;
	}

	
}
