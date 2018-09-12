package pool_scheduler.actions;

/**
 * Action about dressing someone
 *
 */
public class DressedAction extends ScenarioAction {
	/**
	 * Action to dress clothes
	 * @param timeToEnd time to totally dress clothes
	 */
	public DressedAction(int timeToEnd) {
		super(timeToEnd, "Dressing");
	}
}
