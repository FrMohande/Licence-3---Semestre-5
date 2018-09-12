package pool_scheduler.actions;

import pool_scheduler.resources.Basket;
import pool_scheduler.resources.BasketPool;
import pool_scheduler.resources.Cubicle;
import pool_scheduler.resources.CubiclePool;
import pool_scheduler.resources.ResourcefulUser;
import pool_scheduler.schedulers.ScenarioScheduler;

/**
 * Represent a swimmer
 *
 */
public class Swimmer extends Action {

	protected String name;
	
	protected BasketPool basketPool;
	protected CubiclePool cubiclePool;
	
	protected int timeForUndress;
	protected int timeForSwim;
	
	protected int timeForDress;
	
	protected ResourcefulUser<Cubicle> userCubicle;
	protected ResourcefulUser<Basket> userBasket;
		
	protected ScenarioScheduler scenarioScheduler;
	
	/**
	 * Actor specialized in swimming
	 * @param name the name of the swimmer
	 * @param basketPool the basketPool to use
	 * @param cubiclePool the cubiclePool to use
	 * @param timeForUndress time necessary to remove clothes
	 * @param timeForSwim time to swim
	 * @param timeForDress time to dress clothes
	 */
	public Swimmer(String name, BasketPool basketPool, CubiclePool cubiclePool, int timeForUndress, int timeForSwim, int timeForDress) {
		this.name = name;
		this.basketPool = basketPool;
		this.cubiclePool = cubiclePool;
		this.timeForUndress = timeForUndress;
		this.timeForSwim = timeForSwim;
		this.timeForDress = timeForDress;
		
		userCubicle = new ResourcefulUser<>();
		userBasket = new ResourcefulUser<>();
		
		this.scenarioScheduler = createScenarioScheduler();
	}
		
	/**
	 * Method to generate the whole process of the scenario of swimmers
	 * @return the scenario of the swimmer
	 */
	protected ScenarioScheduler createScenarioScheduler(){
		ScenarioScheduler resultscenarioScheduler = new ScenarioScheduler();

		resultscenarioScheduler.addAction(new TakeResourceAction<>(userBasket, basketPool, name));
		resultscenarioScheduler.addAction(new TakeResourceAction<>(userCubicle, cubiclePool, name));
		resultscenarioScheduler.addAction(new UnDressedAction(timeForUndress));
		resultscenarioScheduler.addAction(new FreeResourceAction<>(userCubicle, cubiclePool, name));
		resultscenarioScheduler.addAction(new SwimAction(timeForSwim));
		resultscenarioScheduler.addAction(new TakeResourceAction<Cubicle>(userCubicle, cubiclePool, name));
		resultscenarioScheduler.addAction(new DressedAction(timeForDress));
		resultscenarioScheduler.addAction(new FreeResourceAction<>(userCubicle, cubiclePool, name));
		resultscenarioScheduler.addAction(new FreeResourceAction<>(userBasket, basketPool, name));

		return resultscenarioScheduler;
	}
	
	/**
	 * 
	 * @return the name of the swimmer
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return the time to be undressed
	 */
	public int getTimeForUndress() {
		return timeForUndress;
	}

	/**
	 * 
	 * @return the time to swim
	 */
	public int getTimeForSwim() {
		return timeForSwim;
	}

	/**
	 * 
	 * @return the time to dress
	 */
	public int getTimeForDress() {
		return timeForDress;
	}

	
	/**
	 * Put a defined scenario to the swimmer
	 * @param scenarioScheduler the scenario to affect to the swimmer
	 */
	public void setscenarioScheduler(ScenarioScheduler scenarioScheduler) {
		this.scenarioScheduler = scenarioScheduler;
	}

	@Override
	public boolean isReady() {
		return scenarioScheduler.isReady();
	}

	@Override
	public boolean isInProgress() {
		return scenarioScheduler.isInProgress();
	}

	@Override
	public boolean isFinished() {
		return scenarioScheduler.isFinished();
	}
	
	@Override
	public void doStep() {
		scenarioScheduler.doStep();
	}
}
