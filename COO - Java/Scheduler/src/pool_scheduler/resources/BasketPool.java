package pool_scheduler.resources;

/**
 * Class managing basket resources
 *
 */
public class BasketPool extends ResourcePool<Basket> {

	/**
	 * Create a basket manager with a capacity
	 * @param capacity the number of basket than it can take
	 */
	public BasketPool(int capacity) {
		super(capacity);
	}
	
	@Override
	public String toString() {
		return super.toString() + " basket";
	}
}
