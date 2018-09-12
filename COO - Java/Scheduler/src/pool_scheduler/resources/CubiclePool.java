package pool_scheduler.resources;

/**
 * Class managing cubicle resources
 *
 */
public class CubiclePool extends ResourcePool<Cubicle> {

	/**
	 * Create a pool manager with a specified capacity
	 * @param capacity the capacity of cubicle that the pool can take
	 */
	public CubiclePool(int capacity) {
		super(capacity);
	}
	
	@Override
	public String toString() {
		return super.toString() + " cubicle";
	}

}
