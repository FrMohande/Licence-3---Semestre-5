package pool_scheduler.actions;

import pool_scheduler.resources.Resource;
import pool_scheduler.resources.ResourcePool;
import pool_scheduler.resources.ResourcefulUser;

/**
 * Base of an user's action using a resource
 * @param <R> type of the resource to use
 */
public abstract class ResourceAction<R extends Resource> extends Action {

	protected ResourcefulUser<R> resourcefulUser;
	protected ResourcePool<R> resourcePool;

	protected String name;
	/**
	 * Constructor of ResourceAction
	 * @param resourcefulUser User who's doing action
	 * @param resourcePool resource used for the action
	 * @param name Name of the action's user
	 */
	public ResourceAction(ResourcefulUser<R> resourcefulUser, ResourcePool<R> resourcePool, String name) {
		this.resourcefulUser = resourcefulUser;
		this.resourcePool = resourcePool;
		this.name = name;
	}
	
	

}
