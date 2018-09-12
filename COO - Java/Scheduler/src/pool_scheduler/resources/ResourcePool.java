package pool_scheduler.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class ResourcePool<T extends Resource> {
	
	/**
	 * The general resource for pool
	 */
	protected List<T> availableResource = new ArrayList<>();
	
	/**
	 * The used resource
	 */
	protected List<T> usedResources = new ArrayList<>();
	
	/**
	 * The capacity of pool
	 */
	protected int capacity;
	
	public ResourcePool(int capacity){
		this.capacity = capacity;
	}
	
	public T provideResource(T r){
		int index = availableResource.indexOf(r);
		if(index > -1){
			T resource = availableResource.remove(index);
			usedResources.add(resource);
			return resource;
		}
		else {

			if(usedResources.contains(r)) {
				throw new NoSuchElementException("This resource is used");
			}
			throw new IllegalArgumentException("This resource doesn't exist");

		}
	}
	
	public void freeResource(T r){
		int index = usedResources.indexOf(r);
		if(index > -1 ){
			availableResource.add(usedResources.remove(index));
			System.out.println("This resource is unlocked.");
		}
		else{
			System.out.println("This resource is not used.");
		}
	}
	
	public void addResource(T r){
		if(getNTotalResource() < capacity) {
			availableResource.add(r);
		}
		else{
			throw new ArrayIndexOutOfBoundsException("max capacity for ResourcePool");
		}
	}
	
	public T getFirstResource(){
		if(availableResource.size() > 0 )
			return availableResource.get(0);
		return null;
	}
	
	public int getNTotalResource() {
		return getNAvailableResource() + getNUsedResource();
	}
	
	public int getNAvailableResource() {
		return availableResource.size();
	}
	
	public int getNUsedResource() {
		return usedResources.size();
	}

	public int getCapacity() {
		return capacity;
	}

	public List<T> getResources() {
		return availableResource;
	}

	public List<T> getUsedResources() {
		return usedResources;
	}
	
	@Override
	public String toString() {
		return "pool";
	}
	
}