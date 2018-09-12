package pool_scheduler.schedulers;


import java.util.ArrayList;
import java.util.List;

import pool_scheduler.actions.Action;

/**
 * Class to manage a list of actions
 *
 */
public abstract class Scheduler extends Action {
	
	protected List<Action> actions;
	protected boolean isReady = true;
	protected boolean isInit = false;
	
	/**
	 * Create empty Scheduler
	 */
	public Scheduler(){ this(new ArrayList<>()); }	
	
	public Scheduler(List<Action> actions) {
		this.actions = actions;
	}
	
	/**
	 * Add action
	 * @param a Action
	 */
	public void addAction(Action a){
		if(a.isFinished()) {
			throw new IllegalArgumentException("Can't add an already finished action");
		}
		
		this.isInit = true;
		if(isFinished()) {
			throw new IllegalStateException("You can't add action on finished scheduler");
		}
		actions.add(a);
	}
	

	public abstract void nextAction();
	
	/**
	 * 	Remove all action are finished
	 */
	public abstract void remove();
	
	
	public abstract Action getCurrentAction();
	
	
	@Override
	public boolean isReady() {
		return isInit && isReady;
	}

	@Override
	public boolean isInProgress() {
		return isInit && !isReady() && !isFinished();
	}

	@Override
	public boolean isFinished() {
		return !isReady() && actions.isEmpty();
	}


	public List<Action> getActions() {
		return actions;
	}	
}
