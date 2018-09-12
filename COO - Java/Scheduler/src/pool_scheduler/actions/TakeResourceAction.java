package pool_scheduler.actions;

import pool_scheduler.resources.Resource;
import pool_scheduler.resources.ResourcePool;
import pool_scheduler.resources.ResourcefulUser;


/**
 * 
 * Class to put a resource to an user
 *
 * @param <R> type of the resource
 */
public class TakeResourceAction<R extends Resource> extends ResourceAction<R>{
	
	protected boolean takeResource = false;
	
	protected final static String SUCCESS_STATUS = "success";
	
	protected final static String FAIL_STATUS = "failed";


	/**
	 * Action to give a resourcePool to an user
	 * @param resourcefulUser the user who will have the resourcePool
	 * @param resourcePool the resourcePool to affect to the user
	 * @param name Name of the action's user
	 */
	public TakeResourceAction(ResourcefulUser<R> resourcefulUser, ResourcePool<R> resourcePool, String name) {
		super(resourcefulUser, resourcePool, name);
	}
	
	@Override
	public boolean isReady() {
		return takeResource == false;
	}
	
	@Override
	public void doStep() {
		if(resourcefulUser.getResource() != null ){
			throw new IllegalAccessError("The resource of user is not null");
		}
		System.out.print(this);
		R resource = resourcePool.getFirstResource();
		
		try {
			resource = resourcePool.provideResource(resource);
			resourcefulUser.setResource(resource);
			this.takeResource = true;
			System.out.println(SUCCESS_STATUS);
		} catch (Exception e) {
			this.takeResource = false;
			System.out.println(FAIL_STATUS);
		}
		
	}
	
	/**
	 * Define if the resource is taken or not
	 * @param takeResource the status of the resource (true if taken, false if not)
	 */
	protected void setTakeResource(boolean takeResource) {
		this.takeResource = takeResource;
	}
	
	/**
	 * 
	 * @return the actual status of the action
	 */
	protected String getStatus() {
		return isFinished() ? SUCCESS_STATUS : FAIL_STATUS;
	}
	
	@Override
	public boolean isInProgress() {
		return !isReady() && !isFinished();
	}
	
	@Override
	public boolean isFinished() {
		return takeResource == true && resourcefulUser.getResource() != null;
	}
	
	@Override
	public String toString() {
		return name + " trying to take resource from " + resourcePool + "... ";
	}
}