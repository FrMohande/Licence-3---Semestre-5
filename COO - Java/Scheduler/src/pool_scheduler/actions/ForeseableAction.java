package pool_scheduler.actions;


/**
 * Action with time necessary to be considered as finished
 *
 */
public class ForeseableAction  extends Action{
	protected int timeToEnd;
	protected int time;
	
	/**
	 * Create an action with a time to be finished
	 * @param timeToEnd the time for execute action
	 * throw new IllegalArgumentException if timeToEnd = 0
	 */
	public ForeseableAction(int timeToEnd){
		if(timeToEnd == 0){
			throw new IllegalArgumentException("Can't add an already finished action");
		}
		this.timeToEnd = timeToEnd;
		time = 0;
	}
	
	@Override
	public boolean isReady() {
		return time == 0;
	}

	@Override
	public boolean isInProgress() {
		return time > 0 && time < timeToEnd;
	}

	@Override
	public boolean isFinished() {
		return time == timeToEnd;
	}

	@Override
	public void doStep() {
		if(!isFinished()){
			time++;
		}
	}

	public int getTimeToEnd() {
		return timeToEnd;
	}

	public int getTime() {
		return time;
	}
	
	@Override
	public String toString() {
		return "Time : " + time + " TimeEnd : " + timeToEnd;
	}
	
}
