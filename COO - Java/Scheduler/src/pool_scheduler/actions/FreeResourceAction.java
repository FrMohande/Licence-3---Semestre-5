package pool_scheduler.actions;

import pool_scheduler.resources.Resource;
import pool_scheduler.resources.ResourcePool;
import pool_scheduler.resources.ResourcefulUser;

/**
 * Class to free a resource to a user
 *
 * @param <R> type of resource to manage
 */
public class FreeResourceAction<R extends Resource> extends ResourceAction<R>{
	
	public boolean freeResource = false;
	
	/**
	 * Init an action with the goal to free the resourcePool to the specified user
	 * @param resourcefulUser  the user to free to the resourcePool
	 * @param resourcePool the resourcePool to remove from the user
	 * @param name Name of the action's user
	 */
	public FreeResourceAction(ResourcefulUser<R> resourcefulUser, ResourcePool<R> resourcePool, String name) {
		super(resourcefulUser, resourcePool, name);
	}

	@Override
	public void doStep() {
		if(resourcefulUser.getResource() != null) {
			resourcePool.freeResource(resourcefulUser.getResource());
			resourcefulUser.resetResource();
			freeResource = true;
			System.out.println(this);
		}
		
	}

	@Override
	public boolean isReady() {
		return freeResource == false;
	}

	@Override
	public boolean isInProgress() {
		return !isReady() && !isFinished();
	}

	@Override
	public boolean isFinished() {
		return freeResource == true && resourcefulUser.getResource() == null;
	}
	
	@Override
	public String toString() {
		return name + " freeing resource to take resource " + resourcePool;
	}
}
